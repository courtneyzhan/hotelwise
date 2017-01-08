class Booking < ApplicationRecord
  belongs_to :room
  belongs_to :room_type
  belongs_to :customer

  after_save :update_price
  
  # Check if a given interval overlaps this interval    
   def overlaps?(other)
     if (check_in_date - other.check_out_date) * (other.check_in_date - check_out_date) >= 0
       return other.inspect?
     end
   end

   # Return a scope for all interval overlapping the given interval, including the given interval itself
   scope :overlapping, lambda { |interval| 
    where("customer_id <> ? AND room_id = ? AND (DATEDIFF(check_in_date, ?) * DATEDIFF(?, check_out_date)) >= 0", interval.id, interval.room_id, interval.check_out_date, interval.check_in_date)
   }

  
   # every time it saves booking, update price
   # get room type pirce, get how many days
   def update_price
     if room_type && check_out_date && check_in_date
       self.update_column(:total_price, room_type.price * (check_out_date - check_in_date).to_i) 
     end
   end
  
end
