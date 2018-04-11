class CreateBooks < ActiveRecord::Migration
  def change
    create_table :books do |t|
      t.string :title
      t.string :isbn
      t.integer :pages
      t.references :author

      t.timestamps
    end
    add_index :books, :author_id
    add_column :books, :isbn, :string
    
    #remove_reference :books, :author, index: true
  end

  # def down
  #   remove_column :books, :isbn
  # end

end
