class Booking < ApplicationRecord
  belongs_to :room
  belongs_to :customer
  

end
