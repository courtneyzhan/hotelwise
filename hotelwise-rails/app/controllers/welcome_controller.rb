class WelcomeController < ApplicationController

  def home
    # for staff, visit "/admin"
    redirect_to "/search"
  end
  
  def customer_log_in
    if request.post?
      @email = params[:email]
      @password = params[:password]
      if customer = Customer.where(:email => @email, :password => @password).first
        session[:customer_id] = customer.id
        redirect_to "/search"
      end  
    end
  end
  
  def staff_log_in
    if request.post?
      @username = params[:username]
      @password = params[:password]
      if user = User.where(:login => @username, :password => @password).first
        session[:user_id] = user.id        
        redirect_to "/bookings"
      end  
    end
  end
  
  def customer_log_out
      session[:customer_id] = nil
      redirect_to "/search"
  end


  # reset the session and database with seeded data (existing data will be wiped)
  def reset
    session[:user_id] = nil        
    session[:customer_id] = nil
    Rails.cache.clear
    
    resetable_envs = %w(development demo test local integration sandbox)
    raise "Not allowed" unless resetable_envs.include?(Rails.env) || Rails.env =~ /^ci\d$/
    
    load_seed
    redirect_to '/', :notice => "database is reset"
  end
  
  private
  
	def load_seed(filename = "seeds.rb")
	  start_time = Time.now    
    connection = ActiveRecord::Base.connection    
    begin
      seed_file = File.expand_path(File.join(File.dirname(__FILE__), "..", "..", "db", filename))
      puts "About to load seed_file: #{seed_file}"
      
      
      model_list = [Room, RoomType, Customer, User, Booking]
      model_list.each do |model|
        next unless defined?(model)
        if connection.respond_to?(:reset_pk_sequence!)
          connection.reset_pk_sequence!(model.table_name)
        end
        connection.execute("truncate table #{model.table_name}")        
      end      
      load seed_file
    rescue => e
      puts "failed to seed: #{e}"
    end		
    puts "Reset v1 took  #{Time.now - start_time} seconds"		
	end
	
end
