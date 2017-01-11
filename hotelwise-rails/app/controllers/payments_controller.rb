class PaymentsController < ApplicationController
  def receipt    
    @payment = Payment.where(:id => params[:id]).first
    @booking = @payment.booking
  end
end
