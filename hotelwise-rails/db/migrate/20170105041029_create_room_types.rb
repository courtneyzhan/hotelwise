class CreateRoomTypes < ActiveRecord::Migration[5.0]
  def change
    create_table :room_types do |t|
      t.string :name
      t.decimal :price, precision: 8, scale: 2, default: 0.0
      t.string :facility
      t.text :description

      t.timestamps
    end
  end
end
