class CreateRooms < ActiveRecord::Migration[5.0]
  def change
    create_table :rooms do |t|
      t.string :room_number
      t.string :room_type
      t.decimal :price, precision: 8, scale: 2, default: 0.0
      t.string :floor
      t.string :facility
      t.text :description

      t.timestamps
    end
  end
end
