require 'simplecov'
SimpleCov.start

require_relative 'part2'

describe "Jeopardy"  do
	before(:each) do
		@jeopardy = Jeopardy.new
	end

	describe "1_best_picture_company" do
		it "takes a given production company name and returns all movies nominated for the Best Picture award" do			
			results = ["Mary Poppins", "Beauty and the Beast", "Up", "Toy Story 3"]			
			expect(@jeopardy.best_picture_company("Disney")).to eq(results)

			expect(@jeopardy.best_picture_company("Fox")).to include("Seventh Heaven")

			expect(@jeopardy.best_picture_company("Paramount")).to include("The Love Parade")
		end
	end

	describe "2_best_original_screenplay_writer" do
		it "takes a given movie that was nominated/won the Best Original Screenplay award and returns all the writers." do
			results = ["Ennio de Concini", "Pietro Germi", "Alfredo Giannetti"]			
			expect(@jeopardy.best_original_screenplay_writer("Divorce, Italian Style")).to eq(results)

			results = ["W. R. Burnett", "Frank Butler"]	
			expect(@jeopardy.best_original_screenplay_writer("Wake Island")).to eq(results)

			results = ["Myles Connolly"]	
			expect(@jeopardy.best_original_screenplay_writer("Music for Millions")).to eq(results)
		end
	end

	describe "3_best_leading_actor_role" do
		it "returns all actors who was playing a given role and nominated for a Best Leading Actor" do			
			expect(@jeopardy.best_leading_actor_role("King")).to include("Charles Laughton")

			expect(@jeopardy.best_leading_actor_role("Nickie Elkins")).to include("Richard Barthelmess")

			expect(@jeopardy.best_leading_actor_role("Alec Leamas")).to include("Richard Burton")
		end
	end

	describe "4_best_leading_actress_year" do
		it "takes a given year and returns all actresses nominated for a Best Leading Actress award along with the movie and their age that year" do			
			results = {"name"=>"Helen Mirren", "movie"=>"The Queen", "age"=>61}
			expect(@jeopardy.best_leading_actress_year(2006)).to include(results)	

			results = {"name"=>"Marie Dressler", "movie"=>"Min and Bill", "age"=>62}
			expect(@jeopardy.best_leading_actress_year(1930)).to include(results)

			results = {"name"=>"Julie Andrews", "movie"=>"Min and Bill", "age"=>29}
			expect(@jeopardy.best_leading_actress_year(1964)).to include(results)
		end
	end

	describe "5_best_director_number" do
		it "returns all directors that have been nominated for at least a given times Best Director awards" do			
			results = {"John Ford"=>["The Informer", "Stagecoach", "The Grapes of Wrath", "How Green Was My Valley", "The Quiet Man"]}			
			expect(@jeopardy.best_director_number(4)).to include(results)	

			results = {"William Wyler"=>["Dodsworth", "Wuthering Heights", "The Letter", "The Little Foxes", "Mrs. Miniver", "The Best Years of Our Lives", "The Heiress", "Detective Story", "Roman Holiday", "Friendly Persuasion", "Ben-Hur", "The Collector"]}
			expect(@jeopardy.best_director_number(12)).to eq(results)	

			results = {"Woody Allen"=>["Annie Hall", "Interiors", "Broadway Danny Rose", "Hannah and Her Sisters", "Crimes and Misdemeanors", "Bullets over Broadway", "Midnight in Paris"]}
			expect(@jeopardy.best_director_number(7)).to include(results)	
		end
	end

	describe "6_best_foreign_language_film_country" do
		it "returns the country that has been nominated the most number of times for Best Foreign Language Film award" do			
			results = @jeopardy.best_foreign_language_film_country
			
			results = @jeopardy.country_find()
			expect(results).to include({"United Kingdom"=>2})
			expect(results).to include({"Chile"=>1})
			expect(results).to include({"Russia"=>6})	

			results = {"Italy"=>31, "France"=>39, "Japan"=>15, "West Germany"=>8, "Denmark"=>10, "India"=>3, "Norway"=>5, "Spain"=>19, "Socialist Federal Republic of Yugoslavia"=>6, "Netherlands"=>7, "Sweden"=>14, "Mexico"=>8, "Greece"=>5, "Brazil"=>4, "Poland"=>10, "Israel"=>10, "Czechoslovakia"=>6, "Soviet Union"=>9, "Hungary"=>8, "Algeria"=>5, "Switzerland"=>5, "Belgium"=>7, "Argentina"=>7, "Ivory Coast"=>1, "East Germany"=>1, "Nicaragua"=>1, "Canada"=>7, "Austria"=>4, "Puerto Rico"=>1, "China"=>2, "Germany"=>9, "Iceland"=>1, "Hong Kong"=>2, "Russia"=>6, "Uruguay"=>1, "United Kingdom"=>2, "Vietnam"=>1, "Taiwan"=>3, "Republic of Macedonia"=>1, "Cuba"=>1, "Czech Republic"=>3, "Georgia"=>1, "Iran"=>2, "Nepal"=>1, "Bosnia and Herzegovina"=>1, "Finland"=>1, "South Africa"=>2, "State of Palestine"=>2, "Kazakhstan"=>1, "Peru"=>1, "Chile"=>1, "Cambodia"=>1, "Estonia"=>1, "Mauritania"=>1}
			country_max = @jeopardy.country_max(results)

			expect(country_max[:country]).to eq(["France"])
			expect(country_max[:number]).to eq([39])
			expect(country_max[:movie]).to include("Monsieur Vincent")
			expect(country_max[:movie]).to include("The Walls of Malapaga")
			expect(country_max[:movie]).to include("East-West")
		end
	end

	describe "7_movies_award_actor" do
		it "returns all movies nominated for a given award that starred given actor" do				 
			expect(@jeopardy.movies_award_actor("Best Actress", "Vivien Leigh")).to include("Gone with the Wind")

			expect(@jeopardy.movies_award_actor("Best Director", "Steven Spielberg")).to include("Schindler's List")
			
			expect(@jeopardy.movies_award_actor("Best Animated Feature", "Tom Hanks")).to include("Toy Story 3")
		end
	end

	describe "8_best_actor_times" do
		it "finds how many times a given actor/actress has been nominated for the Best Actor/Actress award and list all of the movies" do			
			expect(@jeopardy.best_actor_times("Best Actor", "Marlon Brando")).to include("A Streetcar Named Desire")

			expect(@jeopardy.best_actor_times("Best Actor", "Tom Hanks")).to include("The Philadelphia Story")

			expect(@jeopardy.best_actor_times("Best Actress", "Jennifer Lawrence")).to include("Silver Linings Playbook")
		end
	end

	describe "9_best_picture_director_actor_actress" do
		it "return all movies that were nominated for Best Picture, Best Director, Best Leading Actor, and Best Leading Actress awards in the same year" do			
			results = @jeopardy.best_picture_director_actor_actress()

			movies = {"Mrs. Miniver"=>6}
			expect(results).to include(movies)

			movies = {"Gone With the Wind"=>8, "Goodbye, Mr. Chips"=>1}
			expect(results).to include(movies)

			movies =  {"Rocky"=>3, "Network"=>4}
			expect(results).to include(movies)
		end
	end

end


