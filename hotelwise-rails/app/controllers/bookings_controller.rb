class BookingsController < ApplicationController
  before_action :set_booking, only: [:show, :edit, :update, :destroy]
  before_action :set_customer

  
  # GET /bookings
  # GET /bookings.json
  def index
    @bookings = Booking.all
  end

  # GET /bookings/1
  # GET /bookings/1.json
  def show
  end

  # GET /bookings/new
  def new
    @booking = Booking.new
  end

  # GET /bookings/1/edit
  def edit
  end
  

  # POST /bookings
  # POST /bookings.json
  def create
    @booking = Booking.new(booking_params)
    Room.where(:room_type_id => @booking.room_type_id).each do |room| 
      @booking.room = room  
      if (Booking.overlapping(@booking)).size == 0
        respond_to do |format|
          if @booking.save
            format.html {
               redirect_to @booking, notice: 'Booking was successfully created.' 
               return;
            }
            format.json { render :show, status: :created, location: @booking }
          else
            format.html { 
              render :new 
              return
            }
            format.json { render json: @booking.errors, status: :unprocessable_entity }
          end
        end
      end
      
    end

  end

  # PATCH/PUT /bookings/1
  # PATCH/PUT /bookings/1.json
  def update
    respond_to do |format|
      if @booking.update(booking_params)
        format.html { redirect_to @booking, notice: 'Booking was successfully updated.' }
        format.json { render :show, status: :ok, location: @booking }
      else
        format.html { render :edit }
        format.json { render json: @booking.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /bookings/1
  # DELETE /bookings/1.json
  def destroy
    @booking.destroy
    respond_to do |format|
      format.html { redirect_to bookings_url, notice: 'Booking was successfully destroyed.' }
      format.json { head :no_content }
    end
  end
  
  def check

    puts "Received params: #{params.inspect}"
    @num_of_adults = params[:numofadult]
    @num_of_children = params[:numofchildren]
    @arrive = params[:arrive]
    @depart= params[:depart]
    @room_type= params[:roomtype]
    
    if @num_of_adults.nil? || @num_of_adults.to_i < 1
      redirect_to "/search", :alert => "At least one adult be present."
      return
    end  
    if @arrive.nil? || @arrive.blank?
      redirect_to "/search", :alert => "Please enter the arrival date"
      return
    end
    if @depart.nil? || @depart.blank?
      redirect_to "/search", :alert => "Please enter the departure date"
      return
    end
    @arrive = Date.strptime(@arrive, "%Y-%m-%d")
    @depart = Date.strptime(@depart, "%Y-%m-%d")
    if @arrive.past?()
      redirect_to "/search", :alert => "You must arrive after today's date"
      return
    end
    
    if @depart <= @arrive
      redirect_to "/search", :alert => "You must depart after arrival date"
      return
    end
    
    # after simple validation, now check availalbity 
    @possible_new_booking = Booking.new
    @possible_new_booking.num_of_adults = @num_of_adults
    @possible_new_booking.num_of_children = @num_of_children
    @possible_new_booking.check_in_date = @arrive
    @possible_new_booking.check_out_date = @depart
    @possible_new_booking.room_type_id = @room_type
    puts "\n\n\n possilble new from room type |#{@room_type}| => #{@possible_new_booking.inspect}"
    
    @room_type = RoomType.where(:id => params[:roomtype]).first
    @has_vacancy = false
    Room.where(:room_type_id => @room_type).each do |room| 
      @possible_new_booking.room = room  
      if (Booking.overlapping(@possible_new_booking)).size == 0
        @has_vacancy = true
        break
      end      
    end
    
    if @has_vacancy
      # go to next page
      
    else
      # got search page with error
      redirect_to "/search", alert: "No rooms available"
    end
    
  end

  private

    # Use callbacks to share common setup or constraints between actions.
    def set_booking
      @booking = Booking.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def booking_params
      params.require(:booking).permit(:room_id, :room_type_id, :customer_id, :check_in_date, :check_out_date, :total_price, :num_of_adults, :num_of_children)
    end
end
