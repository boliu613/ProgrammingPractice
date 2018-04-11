Given(/^I'm on the author creation page$/) do
  visit(new_author_path)
end

When(/^I add a new author$/) do
  fill_in 'Name', :with => "Charles Dickens"
  fill_in 'Gender', :with => "Male"
  click_button 'Create Author'
end

Then(/^I should be able to see the new author's page$/) do
  assert page.has_content?("Author was successfully created")
end
