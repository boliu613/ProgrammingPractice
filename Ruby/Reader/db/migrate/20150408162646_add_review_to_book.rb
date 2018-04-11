class AddReviewToBook < ActiveRecord::Migration
  def change
    add_column :books, :review, :string
  end
end
