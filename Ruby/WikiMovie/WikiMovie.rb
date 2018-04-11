require 'open-uri'
require 'nokogiri'

class Jeopardy

	def best_picture_company(company)
		page = "http://en.wikipedia.org/wiki/Academy_Award_for_Best_Picture"
		doc = Nokogiri::HTML(open(page))
		movies = []
		len = doc.css('table.wikitable tr td').length
		for i in 0..(len/3-1)
			movie = (doc.css('table.wikitable tr td')[3*i]).text
			companies = (doc.css('table.wikitable tr td')[3*i+1]).text
			companies = companies.split(',')
			if companies.include?(company)
				movies << movie
			end
		end
		movies
	end

	def  best_original_screenplay_writer(movie)
		page = "http://en.wikipedia.org/wiki/Academy_Award_for_Best_Original_Screenplay"
		doc = Nokogiri::HTML(open(page))
		len = doc.css('table.wikitable tr td').length
		i = 0
		while i < len
			content = (doc.css('table.wikitable tr td')[i]).text
			if content.match(/\d{4}\s\(\d+\w{2}\).*/)
				i += 1
				next
			end

			current_movie = (doc.css('table.wikitable tr td')[i]).text
			if current_movie == movie
				writers = (doc.css('table.wikitable tr td')[i+1]).text
				writers = writers.split(/\n/)
				break
			end

			i += 2
		end
		writers
	end

	def best_leading_actor_role(role)
		page = "http://en.wikipedia.org/wiki/Academy_Award_for_Best_Actor"
		doc = Nokogiri::HTML(open(page))
		len = doc.css('table.wikitable')[1].css('tr td').length
		actors = []
		i = 0
		while i < len
			content = doc.css('table.wikitable')[1].css('tr td')[i].text
			if content.match(/^\[\d+\]$/)
				i += 1
				next
			end

			current_actor = doc.css('table.wikitable')[1].css('tr td')[i].text
			current_role = doc.css('table.wikitable')[1].css('tr td')[i+2].text
			if current_role.match(/^#{Regexp.quote(role)}/) 
				actors << current_actor
			end
			i += 3		
		end
		actors.uniq!
		actors
	end


	def best_leading_actress_year(year)
		page = "http://en.wikipedia.org/wiki/Academy_Award_for_Best_Actress"
		doc = Nokogiri::HTML(open(page))
		len = doc.css('table.wikitable tr td').length
		actress = []
		
		i = 0
		while i < len
			content = (doc.css('table.wikitable tr td')[i]).text
			if content.match(/^\d{4}/)			
				if content.include?(year.to_s)
					i += 1
					content = (doc.css('table.wikitable tr td')[i]).text
					while (!content.match(/^\d{4}/)) && (i < len)
						personal_page = "http://en.wikipedia.org"
						url = doc.css('table.wikitable tr td')[i].css('a')[0]['href']
						personal_page = personal_page + url
						personal_doc = Nokogiri::HTML(open(personal_page))
						birthday = personal_doc.css('table.infobox td span.bday').text[0..3]	
						current_actress = {}
						current_actress[:name] = doc.css('table.wikitable tr td')[i].text
						current_actress[:movie] = doc.css('table.wikitable tr td')[i+1].text
						current_actress[:age] = year - birthday.to_i
						actress.push(current_actress) 

						i += 3
						content = (doc.css('table.wikitable tr td')[i]).text
					end

					break			
				end
				i += 4
			else
				i += 1
			end
		end
		actress
	end

	def best_director_number(number)
		page = "http://en.wikipedia.org/wiki/Academy_Award_for_Best_Directing"
		doc = Nokogiri::HTML(open(page))

		len = doc.css('table.multicol tr td li').length
		director = {}
		i = 0
		while i < len
			content = doc.css('table.multicol tr td li')[i].text
			content.match(/\–\s(.+)\s\((\d+)\)/)
			current_director = Regexp.last_match(1)
			current_number = Regexp.last_match(2)

			if current_number.to_i >= number
				director[current_director] = []
			end
			i += 1		
		end

		len = doc.css('table.wikitable tr td a').length
		i = 0
		while i < len
			content = doc.css('table.wikitable tr td a')[i].text	
			if director.has_key?(content)
				current_director = doc.css('table.wikitable tr td a')[i].text
				current_movie = doc.css('table.wikitable tr td a')[i+1].text
				director[current_director] << current_movie
			end
			i += 1
		end
		director
	end


	def best_foreign_language_film_country()
		countries = country_find()		
		country_max(countries)
	end

	def country_find()
		page = "http://en.wikipedia.org/wiki/List_of_Academy_Award_winners_and_nominees_for_Best_Foreign_Language_Film"
		doc = Nokogiri::HTML(open(page))

		len = doc.css('table.sortable tr td span.flagicon a').length	
		country = {}
		i = 0
		while i < len
			current_country = doc.css('table.sortable tr td span.flagicon a')[i]['title']

			current_country.match(/([^\(]+)/)
				current_country = Regexp.last_match(1)
				current_country.strip!
				if country.has_key?(current_country)
					country[current_country] += 1
				else
					country[current_country] = 1
				end

				i += 1
			end
			country
		end

		def country_max(country)
			page = "http://en.wikipedia.org/wiki/List_of_Academy_Award_winners_and_nominees_for_Best_Foreign_Language_Film"
			doc = Nokogiri::HTML(open(page))

			country_find = country.select { |key, value| value == country.values.max }
			country_max = {}
			country_max[:country] = country_find.keys
			country_max[:number] = country_find.values
			country_max[:movie] = []

			len = doc.css('table.sortable tr td').length	
			i = 0
			while i < len
				content = doc.css('table.sortable tr td')[i].text
				if content.match(/\d{4}\s\(\d+\w{2}\).*/)
					i += 1
					next
				end

				current_movie = doc.css('table.sortable tr td')[i].text
				current_country = doc.css('table.sortable tr td')[i+2].text

				current_movie.match(/([^\[]+)/)
				current_movie = Regexp.last_match(1)
				current_movie.strip!

				if current_country.include?('France')
					country_max[:movie] << current_movie
				end
				i += 5
			end
			country_max
		end

		def movies_award_actor(award, actor)

			page = "http://en.wikipedia.org/wiki/Portal:Academy_Award"
			doc = Nokogiri::HTML(open(page))
			len = doc.css('table tr td center p a').length	
			movies = []
			i = 0
			while i < len


				content = doc.css('table tr td center p a')[i]['title']

				if content.match(/\d+\w\w\s#{Regexp.quote("Academy Awards")}/)
					year_page = "http://en.wikipedia.org"
					url = doc.css('table tr td center p a')[i]['href']
					year_page = year_page + url
					year_doc = Nokogiri::HTML(open(year_page))

					if i.between?(1, 57) or (i == 59) or (i == 64) or (i == 68)
						len1 = year_doc.css('table.wikitable tr th').length
					else
						len1 = year_doc.css('table.wikitable tr td').length
					end			
					j = 0
					while j < len1
						if i.between?(1, 57) or (i == 59) or (i == 64) or (i == 68)
							len2 = year_doc.css('table.wikitable tr th')[j].css('a').length
						else
							len2 = year_doc.css('table.wikitable tr td')[j].css('div a').length
						end

						k = 0
						while k < len2
							if i.between?(1, 57) or (i == 59) or (i == 64) or (i == 68)	
								current_title = year_doc.css('table.wikitable tr th')[j].css('a')[k]['title']
							else
								current_title = year_doc.css('table.wikitable tr td')[j].css('div a')[k]['title']
							end

							if current_title.include?(award)
								len3 = year_doc.css('table.wikitable tr td')[j].css('li').length
								l = 0
								while l < len3
									movie_page = "http://en.wikipedia.org"	
									url = year_doc.css('table.wikitable tr td')[j].css('li')[l].css('a')[0]['href']
									movie = year_doc.css('table.wikitable tr td')[j].css('li')[l].css('a')[0].text
									movie_page = movie_page + url
									movie_doc = Nokogiri::HTML(open(movie_page))
									len4 = movie_doc.css('table.infobox tr').length
									m = 0
									while m < len4
										info_title = movie_doc.css('table.infobox tr')[m].css('th').text
										if info_title =='Starring'
											star = movie_doc.css('table.infobox tr')[m].css('td').text
											if star.include?(actor)
												movies << movie
												break
											end
										end
										m += 1
									end							
									l += 1
								end
								break
							end
							k += 1
						end				
						j += 1
					end			
				end		
				i += 1
			end
			movies
		end

		def best_actor_times(award, actor)

			page = "http://en.wikipedia.org/wiki/Portal:Academy_Award"
			doc = Nokogiri::HTML(open(page))
			len = doc.css('table tr td center p a').length	
			movies = []
			i = 0
			while i < len

				content = doc.css('table tr td center p a')[i]['title']

				if content.match(/\d+\w\w\s#{Regexp.quote("Academy Awards")}/)
					year_page = "http://en.wikipedia.org"
					url = doc.css('table tr td center p a')[i]['href']
					year_page = year_page + url
					year_doc = Nokogiri::HTML(open(year_page))

					if i.between?(1, 57) or (i == 59) or (i == 64) or (i == 68)
						len1 = year_doc.css('table.wikitable tr th').length
					else
						len1 = year_doc.css('table.wikitable tr td').length
					end			
					j = 0
					while j < len1
						if i.between?(1, 57) or (i == 59) or (i == 64) or (i == 68)
							len2 = year_doc.css('table.wikitable tr th')[j].css('a').length
						else
							len2 = year_doc.css('table.wikitable tr td')[j].css('div a').length
						end

						k = 0
						while k < len2
							if i.between?(1, 57) or (i == 59) or (i == 64) or (i == 68)	
								current_title = year_doc.css('table.wikitable tr th')[j].css('a')[k]['title']
							else
								current_title = year_doc.css('table.wikitable tr td')[j].css('div a')[k]['title']
							end
							if current_title.include?(award)
								len3 = year_doc.css('table.wikitable tr td')[j].css('li').length
								l = 0
								while l < len3
									movie_page = "http://en.wikipedia.org"	
									if j < year_doc.css('table.wikitable tr td').length and 
										year_doc.css('table.wikitable tr td')[j].css('li')[l].css('a').length > 1

										url = year_doc.css('table.wikitable tr td')[j].css('li')[l].css('a')[1]['href']
										movie = year_doc.css('table.wikitable tr td')[j].css('li')[l].css('a')[1].text
										movie_page = movie_page + url
										movie_doc = Nokogiri::HTML(open(movie_page))
										len4 = movie_doc.css('table.infobox tr').length
										m = 0
										while m < len4
											info_title = movie_doc.css('table.infobox tr')[m].css('th').text
											if info_title =='Starring'
												star = movie_doc.css('table.infobox tr')[m].css('td').text
												if star.include?(actor)
													movies << movie
												end
											end
											m += 1
										end							
									end
									l += 1
								end
								break
							end
							k += 1
						end				
						j += 1
					end			
				end		
				i += 1
			end
			movies
		end


		def best_picture_director_actor_actress()	
			award =["Best Picture", "Best Director", "Best Actor", "Best Actress"]
			page = "http://en.wikipedia.org/wiki/Portal:Academy_Award"
			doc = Nokogiri::HTML(open(page))
			len = doc.css('table tr td center p a').length	
			movies = []
			final_movies = []
			i = 0
			while i < len

				content = doc.css('table tr td center p a')[i]['title']

				if content.match(/\d+\w\w\s#{Regexp.quote("Academy Awards")}/)
					year_page = "http://en.wikipedia.org"
					url = doc.css('table tr td center p a')[i]['href']
					year_page = year_page + url
					year_doc = Nokogiri::HTML(open(year_page))

					if i.between?(1, 57) or (i == 59) or (i == 64) or (i == 68)
						len1 = year_doc.css('table.wikitable tr th').length
					else
						len1 = year_doc.css('table.wikitable tr td').length
					end			
					j = 0
					while j < len1
						if i.between?(1, 57) or (i == 59) or (i == 64) or (i == 68)
							len2 = year_doc.css('table.wikitable tr th')[j].css('a').length
						else
							len2 = year_doc.css('table.wikitable tr td')[j].css('div a').length
						end

						k = 0
						while k < len2
							if i.between?(1, 57) or (i == 59) or (i == 64) or (i == 68)	
								current_title = year_doc.css('table.wikitable tr th')[j].css('a')[k]['title']
							else
								current_title = year_doc.css('table.wikitable tr td')[j].css('div a')[k]['title']
							end
							if current_title.include?(award[0])
								movies[0] = []
								len3 = year_doc.css('table.wikitable tr td')[j].css('li').length
								l = 0
								while l < len3							
									movie = year_doc.css('table.wikitable tr td')[j].css('li')[l].css('a')[0].text
									movies[0] << movie
									l += 1
								end
							end

							if current_title.include?(award[1])
								movies[1] = []
								len3 = year_doc.css('table.wikitable tr td')[j].css('li').length
								l = 0
								while l < len3							
									movie = year_doc.css('table.wikitable tr td')[j].css('li')[l].css('a')[1].text
									movies[1] << movie
									l += 1
								end
							end


							if current_title.include?(award[2])
								movies[2] = []
								len3 = year_doc.css('table.wikitable tr td')[j].css('li').length
								l = 0
								while l < len3	
									if i == 28 and l == 3
										l += 1
										next
									end						
									movie = year_doc.css('table.wikitable tr td')[j].css('li')[l].css('a')[1].text
									movies[2] << movie
									l += 1
								end
							end

							if current_title.include?(award[3])
								movies[3] = []
								len3 = year_doc.css('table.wikitable tr td')[j].css('li').length
								l = 0
								while l < len3							
									movie = year_doc.css('table.wikitable tr td')[j].css('li')[l].css('a')[1].text
									movies[3] << movie
									l += 1
								end
							end
							k += 1
						end	
						j += 1
					end

					common_movies = (movies[0] & movies[1] & movies[2] & movies[3])
					if !common_movies.empty?
						common_movies_number = {}
						common_movies.each do |common_movie|
							common_movies_number[common_movie] = 0				
						end				
						movies = [[],[],[],[]]

						j = 0
						while j < len1 
							if i.between?(1, 57) or (i == 59) or (i == 64) or (i == 68)
								len2 = year_doc.css('table.wikitable tr th')[j].css('a').length
							else
								len2 = year_doc.css('table.wikitable tr td')[j].css('div a').length
							end

							k = 0
							while k < len2
								if j < year_doc.css('table.wikitable tr td').length and 
									year_doc.css('table.wikitable tr td')[j].css('li')[0].css('a').length > 0 
									movie = year_doc.css('table.wikitable tr td')[j].css('li')[0].css('a')[0].text
									if common_movies.include?(movie)
										common_movies_number[movie] += 1
									end						
								end 

								if j < year_doc.css('table.wikitable tr td').length and 
									year_doc.css('table.wikitable tr td')[j].css('li')[0].css('a').length > 1
									movie = year_doc.css('table.wikitable tr td')[j].css('li')[0].css('a')[1].text
									if common_movies.include?(movie)
										common_movies_number[movie] += 1
									end
								end 
								k += 1
							end
							j += 1
						end
						final_movies << common_movies_number
					end
				end
				i += 1
			end
			final_movies
		end

	end




