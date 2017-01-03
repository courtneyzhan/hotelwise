class CreateCustomers < ActiveRecord::Migration[5.0]
  def change
    create_table :customers do |t|
      t.string :first_name
      t.string :last_name
      t.date :dob
      t.string :address_1
      t.string :address_2
      t.string :email
      t.string :password
      t.string :credit_card_number
      t.string :credit_card_expiry_month
      t.string :credit_card_expiry_year

      t.timestamps
    end
  end
end
