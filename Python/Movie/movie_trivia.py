import csv

def create_actors_DB(actor_file):
    '''Create a dictionary keyed on actors from a text file'''
    f = open(actor_file)
    movieInfo = {}
    for line in f:
        line = line.rstrip().lstrip()
        actorAndMovies = line.split(',')
        actor = actorAndMovies[0]
        if actor not in movieInfo.keys():
            movieInfo[actor] = set([])
        movies = actorAndMovies[1:]
        cleaned_movies = []
        for movie in movies:
            cleaned_movies.append(movie.lstrip().rstrip())
        movieInfo[actor] = movieInfo.get(actor).union(set(cleaned_movies))
    f.close()
    return movieInfo

def create_ratings_DB(ratings_file):
    '''make a dictionary from the rotten tomatoes csv file'''
    scores_dict = {}
    with open(ratings_file, 'r') as csvfile:
        reader = csv.reader(csvfile)
        reader.next()
        for row in reader:
            scores_dict[row[0]] = [row[1], row[2]]
    return scores_dict


def insert_actor_info(actor, movies, movie_Db): 
    '''This function does an insert/update to the movie_Db dictionary. Actor is a string. Movies is a list of movies that the actor has acted in. This function does not return anything.'''
    if actor not in movie_Db.keys():
        movie_Db[actor] = set([])
    cleaned_movies = []
    for movie in movies:
        cleaned_movies.append(movie.lstrip().rstrip())
    movie_Db[actor] = movie_Db.get(actor).union(set(cleaned_movies))


def insert_rating(movie, ratings, ratings_Db):
    '''This function does an insert/update to the ratings_Db dictionary. movie is the movie name as a string. ratings is a tuple like (critics rating, audience rating).''' 
    ratings_Db[movie] = [ratings[0], ratings[1]]

def delete_movie(movie, movie_Db, ratings_Db):
    '''delete all information from the database that corresponds to this movie. movie is a string. movie_Db is the actor dictionary. ratings_Db is the ratings dictionary.'''
    #delete movie from ratings_Db
    if movie in ratings_Db.keys():
        del ratings_Db[movie]
    else:
        print 'The movie does not exist!'

    #delete movie from movie_Db
    for key in movie_Db.keys():
        if movie in movie_Db[key]:
            movie_Db[key] = movie_Db[key] - set([movie])

def select_where_actor_is(actorName, movie_Db):
    '''given an actor, return the list of all movies.'''
    if actorName in movie_Db.keys():
        return list(movie_Db[actorName])
    else:
        return []

def select_where_movie_is(movieName, movie_Db):
    '''given a movie, return the list of all actors.'''
    lst = []
    for key in movie_Db.keys():
        if movieName in movie_Db[key]:
            lst.append(key)
    return lst

def select_where_rating_is(targeted_rating, comparison, is_critic, ratings_Db):
    '''This function returns a list of movies that satisfy an inequality or equality based on the comparison argument and the targeted rating argument. The comparison argument is either =, > or < and is passed in as a string. The is critic argument is a boolean that represents whether we are interested in the critics rating or the audience rating.'''
    lst = []
    for key in ratings_Db.keys():
        if is_critic:
            if comparison == '>' and (int(ratings_Db[key][0]) > int(targeted_rating)):
                lst.append(key)
            elif comparison == '=' and (int(ratings_Db[key][0]) == int(targeted_rating)):
                lst.append(key)
            elif comparison == '<' and (int(ratings_Db[key][0]) < int(targeted_rating)):
                lst.append(key)
            else :
                pass
        else :
            if comparison == '>' and (int(ratings_Db[key][1])  > int(targeted_rating)):
                lst.append(key)
            elif comparison == '=' and (int(ratings_Db[key][1])  == int(targeted_rating)):
                lst.append(key)
            elif comparison == '<' and (int(ratings_Db[key][1])  < int(targeted_rating)):
                lst.append(key)
            else :
                pass
    return lst

def get_co_actors(actorName, movie_Db):
    '''Given an actor’s name, returns list of all actors that the actor has ever worked with in any movie.'''
    lst = []
    if actorName in movie_Db.keys():
        for key in movie_Db.keys():
            if key != actorName:
                for movie in movie_Db[actorName]:
                    if movie in movie_Db[key]:
                        lst.append(key)
                        break
    return lst

def get_common_movie(actor1, actor2, movie_Db):
    '''returns the movies where both actors were cast.'''
    if (actor1 in movie_Db.keys()) and (actor2 in movie_Db.keys()):
        return list(movie_Db[actor1] & movie_Db[actor2]  )
    else:
        return []

def critics_darling(movie_Db, ratings_Db):
    '''returns a list of actors whose movies have the highest average rotten tomatoes rating as per the critics.''' 
    lst = []
    max = 0
    avg = 0
    sum = 0
    num = 0
    for key in movie_Db.keys():
        sum = 0
        avg = 0
        num = 0
        for movie in movie_Db[key]:
            if movie in ratings_Db.keys():
                sum = sum + int(ratings_Db[movie][0])
                num = num + 1
        if num > 0:
            avg = sum / num
        else:
            avg = 0
        if avg > max:
            max = avg
            lst = []
            lst.append(key)
        elif avg == max:
            lst.append(key)
        else :
            pass
    return lst


def audience_darling(movie_Db, ratings_Db):
    '''returns a list of actors whose movies have the highest average rotten tomatoes rating as per the audience. '''
    lst = []
    max = 0
    avg = 0
    sum = 0
    num = 0
    for key in movie_Db.keys():
        sum = 0
        avg = 0
        num = 0
        for movie in movie_Db[key]:
            if movie in ratings_Db.keys():
                sum = sum + int(ratings_Db[movie][1])
                num = num + 1
        if num > 0:
            avg = sum / num
        else:
            avg = 0

        if avg > max:
            max = avg
            lst = []
            lst.append(key)
        elif avg == max:
            lst.append(key)
        else :
            pass
    return lst

def good_movies(ratings_Db):
    '''returns a set of movies that both critics and the audience have rated >= 85.'''
    lst = []
    for key in ratings_Db.keys():
        if (int(ratings_Db[key][0]) >= 85) and (int(ratings_Db[key][1]) >= 85):
            lst.append(key)
    return lst

def get_common_actors(movie1, movie2, movies_Db):
    '''Given a pair of movies, return a list of actors that acted in both.'''
    lst = []
    for key in movies_Db.keys():
        if (movie1 in movies_Db[key]) and (movie2 in movies_Db[key]):
            lst.append(key)
    return lst


def get_bacon(actor, movie_Db):
    '''Get an actor’s Bacon number.'''
    last_level = {actor}
    current_level = {actor}
    level = 0

    if actor == 'Kevin Bacon':
        return 0
        
    while True:
        for actor_name in last_level:                      
            current_level = current_level.union(set(get_co_actors(actor_name, movie_Db)))
        level += 1

        if 'Kevin Bacon' in current_level:
            return level

        if len(current_level - last_level) == 0:
            return -1
            break

        last_level = current_level

def print_info():
    '''print intro message'''
     
    print 'Please input your choice:'
    print 'a. Insert/update actor information'
    print 'b. Insert/update movie information'
    print 'c. Delete movie from database'
    print 'd. Get actor information'
    print 'e. Get movie information'
    print 'f. Get movie satisfy certain condition'
    print 'g. Get all actors an actor has ever worked with'
    print 'h. Get common movies for a given pair of actors'
    print 'i. Get common actors for a given pair of movies'
    print 'j. Get actors whose movies have the highest average critic score'
    print 'k. Get actors whose movies have the highest average audience score'
    print 'l. Get movies whose critic and audience scores are above or equal to 85.'
    print 'm. Get Bacon number of a given actor'

def string_format(str):
    '''re-capitalize string. 'tom hanks' -> 'Tom Hanks' '''
    lst = str.split()
    lst1 = ['a', 'an', 'the', 'at', 'by', 'for', 'in', 'of', 'on', 'to', 'up', 'and', 'as', 'but', 'it', 'or', 'nor' ]
    lst2 = []
    for word in lst:
        if (word in lst1) and (word is not lst[0]) and (word is not lst[-1]):
            pass
        else:
            word = word[0].upper()+word[1:]
        lst2.append(word)
    str = ' '.join(lst2)
    return str
        

def enter_actor_name():
    actor = raw_input('Enter the name of a actor:')
    while actor =='':
        print "The name of a actor should not be empty!"
        actor = raw_input('Enter the name of a actor:')
    
    actor = string_format(actor.lower())

    return actor    

def enter_movie_name():
    movie = raw_input('Enter the name of a movie:')
    while movie =='':
        print "The name of a movie should not be empty!"
        movie = raw_input('Enter the name of a movie:')

    movie = string_format(movie.lower())

    return movie

def optionA(movie_Db, ratings_Db):
    '''Insert/update actor information'''
    movieList = []
    actor = enter_actor_name()

    while True:
        print 'Enter the name of an movie the actor has acted in and enter e to end input.'
        
        movie = enter_movie_name()
        if movie != 'E':
            movieList.append(movie)
        else:       
            break
        
    insert_actor_info(actor, movieList, movie_Db)
    print 'Insert/update has finished!' 

def optionB(movie_Db, ratings_Db):
    '''Insert/update movie information'''
    movie = enter_movie_name()
    
    critic_score = raw_input('Enter the critics rating of a movie:')
    while not critic_score.isdigit() or int(critic_score) < 0 or int(critic_score) > 100:
        print "The critics rating of a movie should be a number between 0 and 100!"
        critic_score = raw_input('Enter the critics rating of a movie:')


    audience_score = raw_input('Enter the audience rating of a movie:')
    while not audience_score.isdigit() or int(audience_score) < 0 or int(audience_score) > 100:
        print "The audience rating of a movie should be a number between 0 and 100!"
        audience_score = raw_input('Enter the audience rating of a movie:')
            
    insert_rating(movie, (critic_score, audience_score), ratings_Db)
    print 'Insert/update has finished!' 


def optionC(movie_Db, ratings_Db):
    '''Delete movie from database'''
    movie = enter_movie_name()

    delete_movie(movie, movie_Db, ratings_Db)
    print 'Delete has finished!'

def optionD(movie_Db, ratings_Db):
    '''Get actor information'''
    actor = enter_actor_name()

    lst = select_where_actor_is(actor, movie_Db)
    if lst == []:
        print 'There is no information of this actor!'
    else:
        print lst

def optionE(movie_Db, ratings_Db):
    '''Get movie information'''
    movie = enter_movie_name()

    lst = select_where_movie_is(movie, movie_Db)
    if lst == []:
        print 'There is no information of this movie!'
    else:
        print lst

def optionF(movie_Db, ratings_Db):
    '''Get movie satisfy certain condition'''
    choice = raw_input('Is your conditon based on critics rating or audience rating? (c/a)')
    while choice not in 'ac':
        print "Please input a or c!"
        choice = raw_input('Is your conditon based on critics rating or audience rating? (c/a)')
    
    if choice == 'c':
        is_critic = True
    else :
        is_critic = False

    comparison = raw_input('Please enter your comparison condition (</=/>):')
    while comparison not in '<=>':
        print "Please input <, = or >!"
        comparison = raw_input('Please enter your comparison condition (</=/>):')

    targeted_rating = raw_input('Enter the rating condition:')
    while not targeted_rating.isdigit() or int(targeted_rating) < 0 or int(targeted_rating) > 100:
        print "The rating of a movie should be a number between 0 and 100!"
        targeted_rating = raw_input('Enter the rating condition:')

    print 'movies satisfying your condition are:'
    print select_where_rating_is(targeted_rating, comparison, is_critic, ratings_Db)
   


def optionG(movie_Db, ratings_Db):
    '''Get all actors an actor has ever worked with'''
    actor = enter_actor_name()
    lst = get_co_actors(actor, movie_Db)

    if lst == []:
        print 'There is no co-actors of this actor!'
    else:     
        print 'All actors an actor has ever worked with are:'
        print lst

def optionH(movie_Db, ratings_Db):
    '''Get common movies for a given pair of actors'''
    print 'Please enter the name of the first actor.'
    actor1 = enter_actor_name()
    print 'Please enter the name of the second actor.'
    actor2 = enter_actor_name()

    lst = get_common_movie(actor1, actor2, movie_Db)

    if lst == []:
        print 'Not present!'
    else:     
        print 'Common movies of the two actors are:' 
        print lst 

def optionI(movie_Db, ratings_Db):
    '''Get common actors for a given pair of movies'''
    print 'Please enter the name of the first movie.'
    movie1 = enter_movie_name()
    print 'Please enter the name of the second movie.'
    movie2 = enter_movie_name()


    lst = get_common_actors(movie1, movie2, movie_Db)

    if lst == []:
        print 'Not present!'
    else:     
        print 'Common actors of the two movies are:' 
        print lst 

def optionJ(movie_Db, ratings_Db):
    '''Get actors whose movies have the highest average critic score'''
    print 'Actors with the highest average critic score are:'
    print critics_darling(movie_Db, ratings_Db)

def optionK(movie_Db, ratings_Db):
    '''Get actors whose movies have the highest average audience score'''
    print 'Actors with the highest average audience score are:'
    print audience_darling(movie_Db, ratings_Db)

def optionL(movie_Db, ratings_Db):
    '''Get movies whose critic and audience scores are above 85.'''
    print 'Movies with critic and audience scores above 85 are:'
    print good_movies(ratings_Db)

def optionM(movie_Db, ratings_Db):
    '''Get Bacon number of a given actor'''
    actor = enter_actor_name()
    if actor in movie_Db.keys():
        Bacon_num = get_bacon(actor, movie_Db)
        if Bacon_num == -1:
            print 'There are no links between ' + actor + ' and Kevin Bacon!'
        else:
            print 'The Bacon number of ' + actor + 'is ' + str(Bacon_num) +'.'
    else:
        print 'Not present!'    
 
def main():
    '''main function'''
    movie_Db = create_actors_DB('movies.txt')
    ratings_Db = create_ratings_DB('moviescores.csv')
    

    print_info()
    option = raw_input('Enter the letter of your option or enter q to quit.')
    while option not in 'abcdefghijklmq':
        print 'Your option is illegal. Please enter again!'
        print_info()
        option = raw_input('Enter the letter of your option or enter q to quit.')

    while option != 'quit':
        if option == 'a':
            optionA(movie_Db, ratings_Db)
        elif option == 'b':
            optionB(movie_Db, ratings_Db)
        elif option == 'c':
            optionC(movie_Db, ratings_Db)
        elif option == 'd':
            optionD(movie_Db, ratings_Db)
        elif option == 'e':
            optionE(movie_Db, ratings_Db)
        elif option == 'f':
            optionF(movie_Db, ratings_Db)
        elif option == 'g':            
            optionG(movie_Db, ratings_Db)
        elif option == 'h':
            optionH(movie_Db, ratings_Db)
        elif option == 'i':
            optionI(movie_Db, ratings_Db)
        elif option == 'j':
            optionJ(movie_Db, ratings_Db)
        elif option == 'k':
            optionK(movie_Db, ratings_Db)
        elif option == 'l':
            optionL(movie_Db, ratings_Db)
        elif option == 'm':
            optionM(movie_Db, ratings_Db)

        elif option == 'q':
            print 'You have quit the program!'
            break
        else:
            pass
        
        print_info()
        option = raw_input('Enter the letter of your option or enter q to quit.')
        while option not in 'abcdefghijklmq':
            print 'Your option is illegal. Please enter again!'
            print_info()
            option = raw_input('Enter the letter of your option or enter q to quit.')

    
if __name__ == '__main__':
    main()


