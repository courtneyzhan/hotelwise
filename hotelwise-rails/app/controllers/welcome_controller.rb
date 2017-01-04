class WelcomeController < ApplicationController

  def home
    # for staff, visit "/admin"
    redirect_to "search"
  end
  
  def customer_log_in
    if request.post?
      @email = params[:email]
      @password = params[:password]
      if Customer.where(:email => @email, :password => @password).first
        redirect_to "/search"
      end  
    end
  end
  
  def staff_log_in
    if request.post?
      @username = params[:username]
      @password = params[:password]
      if User.where(:login => @username, :password => @password).first
        redirect_to "/bookings"
      end  
    end
  end
end
