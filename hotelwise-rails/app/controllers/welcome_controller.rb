class WelcomeController < ApplicationController
  def home
    
  end
  
  def customer_log_in
    if request.post?
      redirect_to "/search"
    end
  end
  
  def staff_log_in
    if request.post?
      redirect_to "/bookings"
    end
  end
end
