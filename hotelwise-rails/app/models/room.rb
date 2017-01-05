class Room < ApplicationRecord
  has_many :bookings
  belongs_to :room_type
end
