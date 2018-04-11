class CreateReviews < ActiveRecord::Migration
  def change
    create_table :reviews do |t|
      t.string :Review
      t.string :rating
      t.integer :
      t.references :book
      t.string :name

      t.timestamps
    end
    add_index :reviews, :book_id
  end
end
