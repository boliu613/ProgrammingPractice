# authors -  Bo Liu and Zi Chen
from cards import *
import unittest

class testCards(unittest.TestCase):

    def setUp(self):
        self.card1 = Card('a','s')
        self.card2 = Card(10,'h')

        self.deck = Deck()

    def equality(self, card_1, card_2):
        '''return True if two cards are equal otherwise False'''
        return (card_1.get_rank()==card_2.get_rank() and
                card_1.get_suit()==card_2.get_suit())        

    def test_init(self):
        self.assertEqual(self.card1.rank, 'A')
        self.assertEqual(self.card1.suit, 'S')

        self.assertEqual(self.card2.rank, '10')
        self.assertEqual(self.card2.suit, 'H')

        self.assertRaises(ValueError, Card, 1, 'y')
        self.assertRaises(ValueError, Card, 2, 'z')
        self.assertRaises(ValueError, Card, 11, 's')

    def test_get_rank(self):
        self.assertEqual(self.card1.get_rank(), 'A')
        self.assertEqual(self.card2.get_rank(), '10')
        self.assertEqual(Card(2,'c').get_rank(), '2')

    def test_get_suit(self):
        self.assertEqual(self.card1.get_suit(), 'S')
        self.assertEqual(self.card2.get_suit(), 'H')
        self.assertEqual(Card(2,'c').get_suit(), 'C')

    def test_init_deck(self):
        self.assertEqual(len(self.deck.get_deck()), 52)

    def test_get_deck(self):
        self.assertEqual(len(self.deck.get_deck()), 52)

    def test_deal(self):
        self.assertEqual(str(self.deck.deal()), 'KD')
        self.assertEqual(str(self.deck.deal()), 'QD')

    

    







unittest.main()
