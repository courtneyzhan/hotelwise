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
  
end
