class PagesController < ApplicationController
  layout "application"

  before_action :set_customer
  
  def home
  end
  
  
  def search

  end
  
  def confirmation
    @credit_card_number = params[:credit_card_number]
    @credit_card_expiry = params[:credit_card_expiry]
  end
  
  def customer_register
    @first_name = params[:first_name]
    @last_name = params[:last_name]
    @dob = params[:dob]
    @email_address = params[:email_address]
    @confirm_email_address = params[:confirm_email_address]
    @address_1 = params[:address_1]
    @address_2 = params[:address_2]
    @special_req = params[:special_req]
    
    if @email_address != @confirm_email_address
      flash[:notice] = "Post successfully created"
    end
  end

  # Add validation:
  #  - customer count must be present
  #  - arrival date must be present
  #  - departure date ..
  #  - before
  #  - arrival date shall be future date (search rails date comparions. get Date.parse)
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
    puts "Arrive param: #{@arrive}"

    @room_type = RoomType.where(:id => params[:roomtype]).first
    
  end
    
end
