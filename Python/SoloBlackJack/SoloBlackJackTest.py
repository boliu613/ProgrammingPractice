# authors -  Bo Liu and Zi Chen
from SoloBlackJack import *
import unittest

class testBlackJack(unittest.TestCase):    
    
    def setUp(self):
        self.bj = Blackjack()
    
    def test_init(self):
        self.assertEqual(self.bj.table.keys(), ['row1','row2','row3','row4'])
        self.assertEqual(self.bj.table.values(), [[1,2,3,4,5],[6,7,8,9,10],[11,12,13],[14,15,16]])
        self.assertEqual(self.bj.discardList, [17,18,19,20])

    def test_get_table(self):
        self.assertEqual(self.bj.get_table().keys(), ['row1','row2','row3','row4'])
        self.assertEqual(self.bj.get_table().values(), [[1,2,3,4,5],[6,7,8,9,10],[11,12,13],[14,15,16]])

    def test_get_discard(self):
        self.assertEqual(self.bj.get_discard(), [17,18,19,20])

    def test_count(self):
        self.assertEqual(self.bj.count(), 0)

        self.bj.processUserMove(17, Card('K','H'))
        self.assertEqual(self.bj.count(), 0)

        self.bj.processUserMove(1, Card('K','D'))
        self.assertEqual(self.bj.count(), 1)

        self.bj.processUserMove(16, Card('K','S'))
        self.assertEqual(self.bj.count(), 2)

        self.bj.processUserMove(16, Card('K','C'))
        self.assertEqual(self.bj.count(), 2)

    def test_findPosition(self):
        self.assertEqual(self.bj.findPosition(1), 1)
        self.assertEqual(self.bj.findPosition(20), 20)

        self.assertEqual(self.bj.findPosition(21),'error')

        self.bj.processUserMove(17, Card('K','H'))
        self.assertEqual(str(self.bj.findPosition(17)), 'KH')

        self.bj.processUserMove(1, Card('K','D'))
        self.assertEqual(str(self.bj.findPosition(1)), 'KD')

        self.bj.processUserMove(1, Card('A','S'))
        self.assertEqual(str(self.bj.findPosition(1)), 'AS')

    def test_checkErrorMove(self):
        self.assertEqual(self.bj.checkErrorMove(1), 1)
        self.assertEqual(self.bj.checkErrorMove('a'), 1)
        self.assertEqual(self.bj.checkErrorMove('21'), 1)

        self.bj.processUserMove(1, Card('A','S'))
        self.assertEqual(self.bj.checkErrorMove('1'), 2)
        self.bj.processUserMove(17, Card('K','H'))
        self.assertEqual(self.bj.checkErrorMove('17'), 2)

        self.assertEqual(self.bj.checkErrorMove('2'), 3)
        self.assertEqual(self.bj.checkErrorMove('20'), 3)

    def test_processUserMove(self):
        self.bj.processUserMove(1, Card('K','D'))
        self.assertEqual(str(self.bj.table['row1'][0]), 'KD')

        self.bj.processUserMove(16, Card('K','S'))
        self.assertEqual(str(self.bj.table['row4'][2]), 'KS')

        self.bj.processUserMove(20, Card('K','H'))
        self.assertEqual(str(self.bj.discardList[3]), 'KH')

    def test_getValue(self):
        value = self.bj.getValue(Card('J', 's'))
        self.assertEqual(10, value)
        value = self.bj.getValue(Card(10, 's'))
        self.assertEqual(10, value)
        value = self.bj.getValue(Card('a', 's'))
        self.assertEqual(1, value)

    def test_scoreHand(self):
        row = [Card('K', 'D'), Card('A', 'h')]
        score = self.bj.scoreHand(row)
        self.assertEqual(10, score)

        row = [Card('K', 'D'), Card('A', 'h'), Card('J', 's')]
        score = self.bj.scoreHand(row)
        self.assertEqual(7, score)

        row = [Card('K', 'D'), Card('A', 'h'), Card(9, 'h')]
        score = self.bj.scoreHand(row)
        self.assertEqual(5, score)

        row = [Card('K', 'D'), Card('A', 'h'), Card(4, 'h')]
        score = self.bj.scoreHand(row)
        self.assertEqual(1, score)

    def test_scoreGame(self):
        row1 = [Card('K', 'D'), Card(7, 'h'), Card(2, 'd'), Card(6, 's'), Card('J', 'h')]
        row2 = [Card('J', 'c'), Card(9, 's'), Card('Q','h'), Card(4, 'c'), Card(10,'d')]
        row3 = [Card(10,'s'), Card(5, 's'), Card(6, 'c')]
        row4 = [Card(4, 's'), Card('K', 's'), Card(5, 'c')]
        self.bj.table = {'row1': row1, 'row2': row2, 'row3': row3, 'row4': row4}
        self.assertEqual(28, self.bj.scoreGame())

        row1 = [Card('K', 'D'), Card(3, 'h'), Card(4, 'd'), Card(3, 's'), Card('A', 'h')]
        row2 = [Card('A', 'c'), Card(3, 's'), Card(4,'h'), Card(3, 'c'), Card('K','d')]
        row3 = [Card(10,'s'), Card('A', 's'), Card(10, 'c')]
        row4 = [Card(5, 's'), Card(2, 's'), Card(5, 'c')]
        self.bj.table = {'row1': row1, 'row2': row2, 'row3': row3, 'row4': row4}
        self.assertEqual(63, self.bj.scoreGame())

    def test_scoreStatistic(self):
        f = open('highScore.txt','a+')
        f.seek(0)
        f.truncate()
        f.close()

        self.bj.scoreStatistic(30)
        f = open('highScore.txt','a+')
        f.seek(0)
        max_score = f.readline()        
        f.close()
        self.assertEqual(str(30), max_score)

        self.bj.scoreStatistic(40)
        f = open('highScore.txt','a+')
        f.seek(0)
        max_score = f.readline()        
        f.close()
        self.assertEqual(str(40), max_score)

        self.bj.scoreStatistic(30)
        f = open('highScore.txt','a+')
        f.seek(0)
        max_score = f.readline()        
        f.close()
        self.assertEqual(str(40), max_score)


               
unittest.main()
