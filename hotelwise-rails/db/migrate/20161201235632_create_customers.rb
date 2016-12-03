class CreateCustomers < ActiveRecord::Migration[5.0]
  def change
    create_table :customers do |t|

      t.timestamps

      t.string :first_name
      t.string :last_name
      t.string :dob
      t.string :check_in
      t.string :check_out
      #t.boolean :checked_in?
      t.integer :room_num
    end
  end
end
