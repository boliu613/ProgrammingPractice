# == Schema Information
#
# Table name: books
#
#  id         :integer          not null, primary key
#  title      :string(255)
#  isbn       :string(255)
#  pages      :integer
#  author_id  :integer
#  created_at :datetime         not null
#  updated_at :datetime         not null
#  review     :string(255)
#

class Book < ActiveRecord::Base
  belongs_to :author
  attr_accessible :pages, :title, :author_id, :isbn
end
