class CreateBookings < ActiveRecord::Migration[5.0]
  def change
    create_table :bookings do |t|
      t.integer :room_id
      t.integer :customer_id
      t.date :check_in_date
      t.date :check_out_date
      t.timestamps
    end
  end
end
