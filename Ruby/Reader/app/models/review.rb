class Review < ActiveRecord::Base
  belongs_to :book
  attr_accessible :Review, :name, :rating
end
