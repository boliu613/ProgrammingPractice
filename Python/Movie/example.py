def print_info():
    '''print intro message'''
     
    print 'Please input your choice:'
    print 'a. Insert/update actor information'
    print 'b. Insert/update moive information'
    print 'c. Delete moive from database'
    print 'd. Get actor information'
    print 'e. Get movie information'
    print 'f. Get movie satisfy certain condition'
    print 'g. Get all actors an actor has ever worked with'
    print 'h. Get common moives for a given pair of actors'
    print 'i. Get common actors for a given pair of moives'
    print 'j. Get actors whose movies have the highest average critic score'
    print 'k. Get actors whose movies have the highest average audience score'
    print 'l. Get movies whose critic and audience scores are above 85.'
    print 'm. Get Bacon number of a given actor'

def enter_actor_name():
    actor = raw_input('Enter the name of a actor:')
    while actor =='':
        print "The name of a actor should not be empty!"
        actor = raw_input('Enter the name of a actor:')
    
    actor = actor.lower()

    return actor    

def enter_moive_name():
    movie = raw_input('Enter the name of a movie:')
    while movie =='':
        print "The name of a movie should not be empty!"
        movie = raw_input('Enter the name of a movie:')

    movie = movie.lower()

    return movie

def optionA(movie_Db, ratings_Db):
    '''Insert/update actor information'''
    moiveList = []
    actor = enter_actor_name()

    while True:
        print 'Enter the name of an moive the actor has acted in and enter e to end input.'
        
        movie = enter_moive_name()
        if movie != 'e':
            moiveList.append(movie)
        else:       
            break
        
    insert_actor_info(actor, movies, movie_Db)
    print 'Insert/update has finished!' 

def optionB(movie_Db, ratings_Db):
    '''Insert/update movie information'''
    movie = enter_moive_name()
    
    critic_score = raw_input('Enter the critics rating of a movie:')
    while critic_score =='' or critic_score < '0' or critic_score >'100'
        print "The critics rating of a movie should be a number between 0 and 100!"
        critic_score = raw_input('Enter the critics rating of a movie:')


    audience_score = raw_input('Enter the audience rating of a movie:')
    while audience_score =='' or audience_score < '0' or audience_score >'100'
        print "The audience rating of a movie should be a number between 0 and 100!"
        audience_score = raw_input('Enter the audience rating of a movie:')
            
    insert_rating(movie, (critic_score, audience_score), ratings_Db)
    print 'Insert/update has finished!' 


def optionC(movie_Db, ratings_Db):
    '''Delete moive from database'''
    movie = enter_moive_name()

    delete_movie(movie, movie_Db, ratings_Db)
    print 'The moive has been deleted from the database!'

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
    movie = enter_moive_name()

    lst = select_where_actor_is(movie, movie_Db)
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
    while targeted_rating < '0' or targeted_rating >'100'
        print "The rating of a movie should be a number between 0 and 100!"
        targeted_rating = raw_input('Enter the rating condition:')

    print 'Moives satisfying your condition are:'
    print select_where_rating_is(targeted_rating, comparison, is_critic, ratings_Db)
   


def optionG(movie_Db, ratings_Db):
    '''Get all actors an actor has ever worked with'''
    actor = enter_actor_name()
    print 'All actors an actor has ever worked with are:'
    print get_co_actors(actor, movie_Db)

def optionH(movie_Db, ratings_Db):
    '''Get common moives for a given pair of actors'''
    print 'Please enter the name of the first actor.'
    actor1 = enter_actor_name()
    print 'Please enter the name of the second actor.'
    actor2 = enter_actor_name()
    
    print 'Common moives of the two actors are:' 
    print get_common_movie(actor1, actor2, movie_Db)


def optionI(movie_Db, ratings_Db):
    '''Get common actors for a given pair of movies'''
    print 'Please enter the name of the first movie.'
    movie1 = enter_movie_name()
    print 'Please enter the name of the second movie.'
    movie2 = enter_movie_name()
    
    print 'Common actors of the two movies are:' 
    print get_common_actors(movie1, movie2, movie_Db)

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
    print get_bacon(actor, movie_Db):


def main():
    
    movieInfo = {}   
    createDb()

    print_info()
    option = raw_input('Enter the letter of your option or enter q to quit.')
    while option not in 'abcdefghijklmq':
        print 'Your option is illegal. Please enter again!'
        print_info()
        option = raw_input('Enter the letter of your option or enter q to quit.')

    while option != 'quit':
        if option == 'a':
            optionA()
        elif option == 'b':
            optionB()
        elif option == 'c':
            optionC()
        elif option == 'd':
            optionD()
        elif option == 'e':
            optionE()
        elif option == 'f':
            optionF()
        elif option == 'g':            
            optionG()
        elif option == 'h':
            optionH()
        elif option == 'i':
            optionI()
        elif option == 'j':
            optionJ()
        elif option == 'k':
            optionK()
        elif option == 'l':
            optionL()
        elif option == 'm':
            optionM()

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