class Booking < ApplicationRecord
  belongs_to :room
  belongs_to :customer
  
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

end
