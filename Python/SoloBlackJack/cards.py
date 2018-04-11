# authors -  Bo Liu and Zi Chen
import random  # needed for shuffling a Deck

class Card(object):
    #the card has a suit which is one of 'S','C','H' or 'D'
    #the card has a rank 
    
    def __init__(self, r, s):
        #implement
        #where r is the rank, s is suit
        if r in set([2,3,4,5,6,7,8,9,10,'J','j','Q','q','K','k','A','a']) and s in set(['S','s','C','c','H','h','D','d']):
            temp = str(r)
            self.rank = temp.capitalize()
            self.suit = s.capitalize()
        else:
            raise ValueError

    def __str__(self):
        return self.rank + self.suit

    def get_rank(self):
        return self.rank

    def get_suit(self):
        return self.suit

class Deck(object):
    """Denote a deck to play cards with"""
     
    def __init__(self):
        """Initialize deck as a list of all 52 cards:
           13 cards in each of 4 suits"""
        self.__deck = []
        for s in ['S','C','H','D']:
            for r in ['A',2,3,4,5,6,7,8,9,10,'J','Q','K']:
                self.__deck.append(Card(r,s))          
        

    def shuffle(self):
        """Shuffle the deck"""
        random.shuffle(self.__deck)

    def get_deck(self):
        if self.__deck == []:
            raise NotImplementedError

        return self.__deck

    def deal(self):
        # get the last card in the deck
        # simulates a pile of cards and getting the top one
        if self.__deck == []:
            raise NotImplementedError
        return self.__deck.pop()
    
    def __str__(self):
        """Represent the whole deck as a string for printing -- very useful during code development"""
        # the deck is a list of cards
        # this function just calls str(card) for each card in list
        # put a '\n' between them
        string = ''
        for index in range(0,len(self.__deck)):
            string += str(self.__deck[index]) + '\n'
        return string
