# == Schema Information
#
# Table name: searches
#
#  id         :integer          not null, primary key
#  name       :string(255)
#  created_at :datetime         not null
#  updated_at :datetime         not null
#

class Search < ActiveRecord::Base
  attr_accessible :name

  def hello
	  @results ||= search_results
  end

private
  def search_results
  	results = Author.order(:name)
  	results = results.where("name like ?", "%#{name}%") if name.present?
  	results
  end
end
