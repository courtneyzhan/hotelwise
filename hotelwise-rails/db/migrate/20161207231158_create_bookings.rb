class CreateBookings < ActiveRecord::Migration[5.0]
  def change
    create_table :bookings do |t|
      t.integer :room_id
      t.integer :room_type_id
      t.integer :customer_id
      t.date :check_in_date
      t.date :check_out_date
      t.decimal :total_price, precision: 8, scale: 2, default: 0.0
      t.integer :num_of_adults
      t.integer :num_of_children
      t.timestamps
    end
  end
end
