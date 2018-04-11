# authors -  Dawei Ju and Bo Liu
import random

global deck
global discard

class myStatic:
    hand_status = hand_status = [False] * 10
    status_check = False

deck = range(1,61)
discard = []


def shuffle():
    '''This function shuffles the deck or the discard pile\
    1.initial game: shuffle the deck\
    2.There is no card in deck. '''
    global deck
    global discard
    if len(deck) == 60:
        #Initial game shuffle deck
        random.shuffle(deck)

    if len(deck) == 0:
        #There is no cards in deck. shuffle the discard's cards
        deck = list(discard)
        random.shuffle(deck)

def check_racko(rack):
    '''Given a rack, determine if Racko has been achieved.'''
    for i in range(0, 9):
        if rack[i] < rack [i+1]:
            return False
    return True

def deal_card():
    '''get the top card from the deck'''
    print "The top card from the deck is:  "
    print deck[len(deck) + 1]
    deck.pop()

def deal_initial_hands():
    ''' Start the game off by dealing two hands of 10 cards each. Returns two lists.'''
    hand1 = []
    hand2 = []
    for i in range(0,10):
        hand1.append(deck.pop())
        hand2.append(deck.pop())
    return hand1, hand2

def does_user_begin():
    ''' simulate a coin toss by using the random library.If it is head,\
    return Ture, and user first. If it is tail, return False, and computer first'''
    head_or_tail = random.randint(1, 2)
    if head_or_tail == 1:
        return True
    else:
        return False

def print_top_to_bottom(rack):
    '''Given a rack print it out from top to bottom.'''
    for i in range(0,10):
        print rack[i]
    print
        

def add_card_to_discard(card):
    '''Add the card to the top of the discard pile.'''
    discard.append(card)

def find_and_replace(newCard, cardToBeReplaced, hand):
    '''find the cardToBeReplaced (represented by a number) in the hand and replace it with newCard.'''
    if cardToBeReplaced not in hand:
        return False
    else:
        #switch the new card and cardToBeReplaced
        replace_index = hand.index(cardToBeReplaced)
        hand[replace_index] = newCard
        discard.append(cardToBeReplaced)
        return True

def user_play(user_hand):
    '''user’s strategy'''
    #check and make sure there are still some cards in the deck
    #else reshuffle the discard and restart.
    if len(deck) == 0:
        #shuffle cards
        shuffle()
        #reveal one card to begin the discard pile
        add_card_to_discard(deck.pop())

    print 'Your current cards:'
    print_top_to_bottom(user_hand)
    print 'The current top card of the discard pile is', discard[-1]
    choice = raw_input ('Do you want this card? (y/n) \n')
    if choice == 'y' or choice == 'Y':
        card = discard.pop()
        #ask the user for the number of the card they want to kick out
        cardToBeReplaced = input('Please input the number you want to kick out.')
        #modify the user’s hand and the discard pile
        while not find_and_replace(card, cardToBeReplaced, user_hand):
            print 'The number you input is not in your hand. Please input again!'
            cardToBeReplaced = input('Please input the number you want to kick out.')
        #print the user’s hand
        print 'Your current cards:'
        print_top_to_bottom(user_hand)
    else:
        card = deck.pop()
        print 'The card you get from the deck is ' + str(card)
        #ask the user if they want this
        secondChoice = raw_input('do you want to keep it? (y/n) \n')
        if secondChoice == 'y' or choice == 'Y':
            #ask the user for the number of the card they want to kick out
            cardToBeReplaced = input('Please input the number you want to kick out.')
            #modify the user’s hand and the discard pile
            while not find_and_replace(card, cardToBeReplaced, user_hand):
                print 'The number you input is not in your hand. Please input again!'
                cardToBeReplaced = input('Please input the number you want to kick out.')
            #print the user’s hand
            print 'Your current cards:'
            print_top_to_bottom(user_hand)
        else:
            add_card_to_discard(card)
            print 'Your current cards:'
            print_top_to_bottom(user_hand)

    print 'You have finished your play!' 
    print
    return user_hand

def computer_play(computer_hand):
    '''computer's strategy'''
    #check and make sure there are still some cards in the deck
    #else reshuffle the discard and restart.
    if len(deck) == 0:
        #shuffle cards
        shuffle()
        #reveal one card to begin the discard pile
        add_card_to_discard(deck.pop())

    #determine the status for each slot
    #ranges of each slot are 60-53, 54-49, 48-43, 42-35, 36-31, 32-23, 24-17, 18-13, 12-7, 6-0    
    if not myStatic.status_check:
        myStatic.status_check = True
        for i in range(0,10):
            #modify the computer’s hand and the discard pile
            if computer_hand[i] <= 6 * (10 - i) and computer_hand[i] > 6 * (9 - i):
                myStatic.hand_status[i] =  True
        for i in range(0,9):
            #modify the computer’s hand and the discard pile
            if myStatic.hand_status[i] and not myStatic.hand_status[i+1] and computer_hand[i+1] > 6 * (9 - i) and computer_hand[i+1] < computer_hand[i]:
                myStatic.hand_status[i+1] =  True
        for i in range(9,0,-1):
            #modify the computer’s hand and the discard pile
            if myStatic.hand_status[i] and not myStatic.hand_status[i-1] and computer_hand[i-1] <= 6 * (10 - i) and computer_hand[i-1] > computer_hand[i]:
                myStatic.hand_status[i-1] =  True 

    index = 0
    #determine the slot card belongs to
    if discard[-1] % 6 == 0:
        index = 9 - (discard[-1] / 6 -1 )
    else:
        index = 9 - (discard[-1] / 6)
    
    if not myStatic.hand_status[index]:
        #modify the computer’s hand and the discard pile
        computer_hand[index], discard[-1] = discard[-1], computer_hand[index]
        myStatic.hand_status[index] = True
    elif computer_hand[index] > discard[-1] and (index+1) < 10 and not myStatic.hand_status[index+1]:
        #modify the computer’s hand and the discard pile
        computer_hand[index+1], discard[-1] = discard[-1], computer_hand[index+1]
        myStatic.hand_status[index+1] = True
    elif computer_hand[index] < discard[-1] and (index-1) >= 0 and not myStatic.hand_status[index-1]:
        #modify the computer’s hand and the discard pile
        computer_hand[index-1], discard[-1] = discard[-1], computer_hand[index-1]
        myStatic.hand_status[index-1] = True
    else:
        #pop card from deck
        card = deck.pop()
        #computer will use this card and calculate the index of hand for this card
        if card % 6 == 0:
            index = 9 - (card / 6 -1)
        else:
            index = 9 - (card / 6)

        if not myStatic.hand_status[index]:
            #modify the computer’s hand and the discard pile
            computer_hand[index], card = card, computer_hand[index]
            myStatic.hand_status[index] = True
        elif computer_hand[index] > card and (index+1) < 10 and not myStatic.hand_status[index+1]:
            #modify the computer’s hand and the discard pile
            computer_hand[index+1], card = card, computer_hand[index+1]
            myStatic.hand_status[index+1] = True
        elif computer_hand[index] < card and (index-1) >= 0 and not myStatic.hand_status[index-1]:
            #modify the computer’s hand and the discard pile
            computer_hand[index-1], card = card, computer_hand[index-1]
            myStatic.hand_status[index-1] = True
        else:
            pass
        #add the replaced card to discard list
        add_card_to_discard(card)

    print 'Computer has finished his play!'
    print
    return computer_hand

def main():
    '''main function'''

    computer_racko = False
    user_racko = False
    print 'Game Start!'
    print
    #shuffle cards
    shuffle()
    #determine whether the user goes first
    userStarts = does_user_begin()
    #deal two hands of 10 cards each
    if userStarts:
        #user first
        print 'Heads! You first!'
        print
        (user_hand, computer_hand) = deal_initial_hands()
    else:
        #computer first
        print 'Tails! Computer first!'
        print
        (computer_hand, user_hand) = deal_initial_hands()
    
    #reveal one card to begin the discard pile
    add_card_to_discard(deck.pop())
    #check racko
    user_racko = check_racko(user_hand)
    computer_racko = check_racko(computer_hand)

    while True:
        if userStarts:
            #user first
            user_hand = user_play(user_hand)
            #check racko
            if check_racko(user_hand):
                user_racko = True
                break

            computer_hand = computer_play(computer_hand)
            #check racko
            if check_racko(computer_hand):
                computer_racko = True
                break
        else:
            #computer first
            computer_hand = computer_play(computer_hand)
            #check racko
            if check_racko(computer_hand):
                computer_racko = True
                break

            user_hand = user_play(user_hand)
            #check racko
            if check_racko(user_hand):
                user_racko = True
                break
                
    if computer_racko:
        print "Computer's current cards:"
        print_top_to_bottom(computer_hand)
        print 'Computer Win!'

    if user_racko:
        print 'You Win!'

    print
    print 'Game Over!'



if __name__ == '__main__':
    main()
