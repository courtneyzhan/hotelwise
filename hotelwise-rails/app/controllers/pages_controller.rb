class PagesController < ApplicationController
  layout "application"
  def home
  end
  
  def confirmation
    $credit_card_number = params[:credit_card_number]
    $credit_card_expiry = params[:credit_card_expiry]
  end
  
  def customer_register
    $first_name = params[:first_name]
    $last_name = params[:last_name]
    $dob = params[:dob]
    $email_address = params[:email_address]
    $confirm_email_address = params[:confirm_email_address]
    $address_1 = params[:address_1]
    $address_2 = params[:address_2]
    $special_req = params[:special_req]
    if $email_address != $confirm_email_address
      flash[:notice] = "Post successfully created"
    end
  end
  
  def check
    $num_of_adults = params[:numofadult]
    $num_of_children = params[:numofchildren]
    $arrive = params[:arrive]
    $depart= params[:depart]
    $room_type= params[:roomtype]
    if $room_type == "singleov"
      $room_type = "Single with Ocean View"
      $price = 30
      redirect_to '/single-ov'
    elsif $room_type == "Single"
      $price = 25
      redirect_to '/single'
    elsif $room_type == "Double"
      $price = 40
      redirect_to '/double'
    elsif $room_type == "Family"
      $price = 50
      redirect_to '/family'
    end
  end
    
end
