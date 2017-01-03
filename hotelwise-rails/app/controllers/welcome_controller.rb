class WelcomeController < ApplicationController
  def home
    
  end
  
  def customer_log_in
    if request.post?
      
      @email = params[:email]
      @password = params[:password]
      if @email == "a@b.c" && @password == 'secret'
        redirect_to "/search"        
      end
      
    end
  end
  
  def staff_log_in
    if request.post?

      redirect_to "/bookings"
    end
  end
end
