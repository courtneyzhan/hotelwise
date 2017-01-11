class CreatePayments < ActiveRecord::Migration[5.0]
  def change
    create_table :payments do |t|
      t.integer :customer_id
      t.integer :booking_id
      t.integer :receipt_id
      t.string :status
      t.decimal :amount, precision: 8, scale: 2, default: 0.0
      t.timestamps
    end
  end
end
