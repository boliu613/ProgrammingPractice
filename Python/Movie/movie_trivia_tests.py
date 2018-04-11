from movie_trivia import *
import unittest
#assertEqual(first, second, msg=None)
#Test that first and second are equal. If the values do not compare equal, the test will fail.
#assertTrue(expr, msg=None)
class TestMovies(unittest.TestCase):
    movie_Db = {}
    rating_Db = {}
    def setUp(self):
        self.movie_Db = create_actors_DB('my_test_movies.txt')
        self.rating_Db = create_ratings_DB('my_test_moviescores.csv')
    #write unit tests for every function.
    def test_insert_actor_info(self):
        #test insert_actor_info 
        insert_actor_info('actor_e', ['movie_a', 'movie_b', 'movie_c', 'movie_d'], self.movie_Db)
        self.assertTrue('actor_e' in self.movie_Db)
        self.assertTrue('movie_a' in self.movie_Db['actor_e'])
        self.assertTrue('movie_b' in self.movie_Db['actor_e'])
        self.assertTrue('movie_c' in self.movie_Db['actor_e'])
        self.assertTrue('movie_d' in self.movie_Db['actor_e'])
    def test_insert_rating(self):
        #test insert_rating 
        insert_rating('movie_e', (100, 100), self.rating_Db)
        self.assertTrue('movie_e' in self.rating_Db)
        self.assertEqual([100, 100], self.rating_Db['movie_e'])
    def test_delete_movie(self):
        #test delete_movie
        delete_movie('movie_a', self.movie_Db, self.rating_Db)
        self.assertTrue('movie_a' not in self.movie_Db['actor_a'])
        self.assertTrue('movie_a' not in self.movie_Db['actor_b'])
        self.assertTrue('movie_a' not in self.movie_Db['actor_c'])
        self.assertTrue('movie_a' not in self.movie_Db['actor_d'])
        self.assertTrue('movie_a' not in self.rating_Db.keys())
    def test_select_where_actor_is(self):
        #test select_where_actor_is
        lst = select_where_actor_is('actor_a', self.movie_Db)
        self.assertEqual(sorted(lst),['movie_a', 'movie_b', 'movie_c', 'movie_d'])
        lst = select_where_actor_is('Nobody', self.movie_Db)
        self.assertEqual(sorted(lst),[])
    def test_select_where_movie_is(self):
        #test select_where_movie_is
        lst = select_where_movie_is('movie_a', self.movie_Db)
        self.assertEqual(sorted(lst),['actor_a', 'actor_b', 'actor_c', 'actor_d'])
        lst = select_where_movie_is('No', self.movie_Db)
        self.assertEqual(sorted(lst),[])
    def test_select_where_rating_is(self):
        #test select_where_rating_is
        lst = select_where_rating_is(10, '=', True, self.rating_Db)
        self.assertEqual(sorted(lst),['movie_a', 'movie_c'])
        lst = select_where_rating_is(10, '>', True, self.rating_Db)
        self.assertEqual(sorted(lst),['movie_b', 'movie_d'])
        lst = select_where_rating_is(90, '<', True, self.rating_Db)
        self.assertEqual(sorted(lst),['movie_a', 'movie_c'])
        lst = select_where_rating_is(90, '>', True, self.rating_Db)
        self.assertEqual(sorted(lst),[])
        lst = select_where_rating_is(10, '=', False, self.rating_Db)
        self.assertEqual(sorted(lst),['movie_b', 'movie_c'])
        lst = select_where_rating_is(10, '>', False, self.rating_Db)
        self.assertEqual(sorted(lst),['movie_a', 'movie_d'])
        lst = select_where_rating_is(90, '<', False, self.rating_Db)
        self.assertEqual(sorted(lst),['movie_b', 'movie_c'])
        lst = select_where_rating_is(90, '>', False, self.rating_Db)
        self.assertEqual(sorted(lst),[])


    def test_get_co_actors(self):
        #test get_co_actors
        lst = get_co_actors('actor_a', self.movie_Db)
        self.assertEqual(sorted(lst),['actor_b', 'actor_c', 'actor_d'])
        lst = get_co_actors('actor_b', self.movie_Db)
        self.assertEqual(sorted(lst),['actor_a', 'actor_c', 'actor_d'])
        lst = get_co_actors('actor_c', self.movie_Db)
        self.assertEqual(sorted(lst),['actor_a', 'actor_b', 'actor_d'])
        lst = get_co_actors('actor_d', self.movie_Db)
        self.assertEqual(sorted(lst),['actor_a', 'actor_b', 'actor_c'])
        lst = get_co_actors('Nobody', self.movie_Db)
        self.assertEqual(sorted(lst),[])
    def test_get_common_movie(self):
        #test get_common_movie
        lst = get_common_movie('actor_a', 'actor_b', self.movie_Db)
        self.assertEqual(sorted(lst),['movie_a', 'movie_b', 'movie_c', 'movie_d'])
        lst = get_common_movie('actor_a', 'actor_c', self.movie_Db)
        self.assertEqual(sorted(lst),['movie_a', 'movie_d'])
        lst = get_common_movie('actor_a', 'actor_d', self.movie_Db)
        self.assertEqual(sorted(lst),['movie_a', 'movie_d'])
        lst = get_common_movie('actor_b', 'actor_c', self.movie_Db)
        self.assertEqual(sorted(lst),['movie_a', 'movie_d'])
        lst = get_common_movie('actor_b', 'actor_d', self.movie_Db)
        self.assertEqual(sorted(lst),['movie_a', 'movie_d'])
        lst = get_common_movie('actor_c', 'actor_d', self.movie_Db)
        self.assertEqual(sorted(lst),['movie_a', 'movie_d'])
        lst = get_common_movie('actor_a', 'actor_e', self.movie_Db)
        self.assertEqual(sorted(lst),[])
        lst = get_common_movie('None1', 'None2', self.movie_Db)
        self.assertEqual(sorted(lst),[])
    def test_critics_darling(self):
        #test critics_darling 
        lst = critics_darling(self.movie_Db, self.rating_Db)
        self.assertEqual(sorted(lst),['actor_a', 'actor_b','actor_c', 'actor_d'])
    def test_audience_darling(self):
        #test audience_darling
        lst = audience_darling(self.movie_Db, self.rating_Db)
        self.assertEqual(sorted(lst),['actor_c', 'actor_d'])
    def test_good_movies(self):
        #test good_movies
        lst = good_movies(self.rating_Db)
        self.assertEqual(sorted(lst),['movie_d'])
    def test_get_common_actors(self):
        #test get_common_actors
        lst = get_common_actors('movie_a', 'movie_b', self.movie_Db)
        self.assertEqual(sorted(lst),['actor_a', 'actor_b'])
        lst = get_common_actors('movie_a', 'movie_e', self.movie_Db)
        self.assertEqual(sorted(lst),[])
        lst = get_common_actors('None1', 'None2', self.movie_Db)
        self.assertEqual(sorted(lst),[])
    def test_get_bacon(self):
        num = get_bacon('Kevin Bacon', self.movie_Db)
        self.assertEqual(num,0)
        num = get_bacon('Nobody', self.movie_Db)
        self.assertEqual(num,-1)
        num = get_bacon('actor_e', self.movie_Db)
        self.assertEqual(num,1)
        num = get_bacon('actor_g', self.movie_Db)
        self.assertEqual(num,3)




unittest.main()
