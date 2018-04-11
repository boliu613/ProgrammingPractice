desc "Import CSV Data"
task :import => :environment do
	require 'csv'

	csv_file_path = 'hello.csv'

	puts "Success1"

	CSV.foreach(csv_file_path, headers: true) do |row|
	  Author.create!({
	    :name => row[0],
	    :gender => row[1],
	    :dob => row[2]
	  })
	  puts row[0]
	  puts "Success2"
	end

	puts "Success3"
end