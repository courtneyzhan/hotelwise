class ApplicationController < ActionController::Base
  protect_from_forgery with: :exception

  def set_customer
    if  session[:customer_id]
      @customer = Customer.where(:id =>  session[:customer_id]).first
    end 
  end

end
