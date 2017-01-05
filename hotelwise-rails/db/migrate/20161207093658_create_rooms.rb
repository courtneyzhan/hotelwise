class CreateRooms < ActiveRecord::Migration[5.0]
  def change
    create_table :rooms do |t|
      t.string :room_number
      t.string :floor
      t.integer :room_type_id

      t.timestamps
    end
  end
end
