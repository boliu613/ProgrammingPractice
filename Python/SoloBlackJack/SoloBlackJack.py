# authors -  Bo Liu and Zi Chen
from cards import *

class Blackjack(object):
    '''build class Blackjack to play this game'''
    def __init__(self):
        row1=[1,2,3,4,5]
        row2=[6,7,8,9,10]
        row3=[11,12,13]
        row4=[14,15,16]
        
        self.table = {'row1':row1,'row2':row2,'row3':row3,'row4':row4}
        self.discardList = [17,18,19,20]

    def __str__(self):
        gamestatus = "Hands: \n"
        for row in self.table.keys():
            gamestatus += row + ": \t"
            if row in ['row3', 'row4']:
                gamestatus += "\t"
            for element in self.table[row]:
                gamestatus += "[" + str(element) + "]" + "\t"
            gamestatus += '\n'

        gamestatus += "\nDiscard: "
        for discard in self.discardList:
            gamestatus += "[" + str(discard) + "]" + "\t"
        
        return gamestatus

    def get_table(self):
        return self.table

    def get_discard(self):
        return self.discardList

    def display(self, dealt_card):
        '''display current game status including table,discard and dealt card'''
        print str(self) + '\n\n' + 'Dealt card: ' + "[" + str(dealt_card) + "]" + "\n"

    def count(self):
        '''count cards which have been placed in table'''
        table = self.get_table()
        num_cards = 0
        for row in table.keys():
            for element in table[row]:
                if type(element)!=int:
                    num_cards += 1
        return num_cards

    def findPosition(self, move):
        '''find and return what in given posion'''
        table = self.get_table()
        discard = self.get_discard()
        
        if move in [1,2,3,4,5]:
            return table['row1'][move-1]
        elif move in [6,7,8,9,10]:
            return table['row2'][move-6]
        elif move in [11,12,13]:
            return table['row3'][move-11]
        elif move in [14,15,16]:
            return table['row4'][move-14]
        elif move in [17,18,19,20]:
            return discard[move-17]
        else:
            return "error"

    def checkErrorMove(self,move):
        '''check if input move is error'''
        
        if move not in ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20']:
            return 1
        else:
            move = int(move)
            if type(self.findPosition(move))!=int:
                return 2
            else:
                return 3

    def processUserMove(self, move, dealt_card):
        '''process a right user move on blackjack'''
        table = self.get_table()
        discard = self.get_discard()
        
        if move in [1,2,3,4,5]:
            table['row1'][move-1] = dealt_card
        elif move in [6,7,8,9,10]:
            table['row2'][move-6] = dealt_card
        elif move in [11,12,13]:
            table['row3'][move-11] = dealt_card
        elif move in [14,15,16]:
            table['row4'][move-14] = dealt_card
        elif move in [17,18,19,20]:
            discard[move-17] = dealt_card

        

    def acceptUserMove(self, dealt_card):
        '''ask for and error check and process user's move'''
        move = raw_input("Please enter an appropriate integer position among table/discard positions: ")
        signal = self.checkErrorMove(move)
        while signal!=3:
            if signal==1:
                print "Not an integer number or Out of range."
            if signal==2:
                print "This position already has a card."
            move = raw_input("Please enter an appropriate integer position among table/discard positions: ")
            signal = self.checkErrorMove(move)
        move = int(move)


        self.processUserMove(move, dealt_card)

    def getValue(self, card):
        '''get the value of a card'''
        value = card.rank
        if value in ['J', 'Q', 'K']:
            value = 10
        if value == 'A':
            value = 1
        return int(value)

    def scoreHand(self, hand):
        '''calculate the score of a hand'''
        hand_count = []
        count = 0
        for card in hand:
            hand_count.append(self.getValue(card))  
        #Ace counts for 11
        count = sum(hand_count)
        if (count < 12) and (1 in hand_count):
            count += 10

        if count == 21:
            if len(hand) == 2:
                score = 10
            else:
                score = 7
        elif count > 21:
            score = 0
        else:
            score = max(count-15, 1)     
        return score
        
    def scoreGame(self):
        '''calculate the score of the table'''
        total_score = 0
        for key in self.table.keys():
            total_score += self.scoreHand(self.table[key])

        total_score += self.scoreHand([self.table['row1'][0], self.table['row2'][0]])
        total_score += self.scoreHand([self.table['row1'][1], self.table['row2'][1], 
                                  self.table['row3'][0], self.table['row4'][0]])
        total_score += self.scoreHand([self.table['row1'][2], self.table['row2'][2], 
                                  self.table['row3'][1], self.table['row4'][1]])
        total_score += self.scoreHand([self.table['row1'][3], self.table['row2'][3], 
                                  self.table['row3'][2], self.table['row4'][2]])
        total_score += self.scoreHand([self.table['row1'][4], self.table['row2'][4]])

        print "Your score is " + str(total_score) + "."
        self.scoreStatistic(total_score)
        return total_score

    def scoreStatistic(self, total_score):
        total_score = str(total_score)

        f = open('highScore.txt','a+')
        f.seek(0)
        max_score = f.readline()

        if (len(max_score) != 0 and total_score > max_score) or len(max_score) == 0:
            print "Congratulations! You created a new record! "
            f.seek(0)
            f.truncate()
            f.write(total_score)
        f.close()

    def play(self):
        '''begin playing this blackjack game'''
        my_deck = Deck()
        my_deck.shuffle()
        dealt_card = my_deck.deal()
        self.display(dealt_card)

        while self.count() < 16 :
            self.acceptUserMove(dealt_card)
            dealt_card = my_deck.deal()
            self.display(dealt_card)

        print "\nAll 16 points of tableau are filled now. Please waiting for your final score.\n"
        self.scoreGame()

def main():
    ''' the main function '''
    replay = True

    while replay:    
        bj_solitaire = Blackjack()
        bj_solitaire.play()
        user_order = raw_input("Do you want to replay? (y/n): ")
        while user_order not in ['n', 'N', 'y', 'Y']:
            print "Please input y or n!"
            user_order = raw_input("Do you want to replay? (y/n): ")
        if user_order in ['n', 'N']:
            replay = False
 
if __name__ == '__main__':
      main()

