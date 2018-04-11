import random

def print_intro():
    '''print introduction information'''       
    print """Congrats, you are the newest ruler of ancient Samaria, elected for a ten year term of office. Your duties are to distribute food, direct farming, and buy and sell lanas needed to support your people. Watch out for rat infestations and the resultant plague! Grain is the general currency, measured in bushels. The following will help you in your decisions:
    * Each person needs at least 20 bushels of grain per year to survive.
    * Each person can farm at most 10 acres of land.
    * It takes 2 bushels of grain to farm an acre of land.
    * The market price for land fluctuates yearly.
Rule wisely and you will be showered with appreciation at the end of your term. Rule poorly and you will be kicked out of office!"""

def ask_to_buy_land(bushels, cost):
    '''Ask user how many bushels to spend buying land.'''
    acres = input("How many acres will you buy? ")
    while acres * cost > bushels:
        print "O great Hammurabi, we have but", bushels, "bushels of grain!"
        acres = input("How many acres will you buy? ")
    return acres

def ask_to_sell_land(acres):
    '''Ask user how much land they want to sell. '''
    acres_to_sell = input("How many acres will you sell? ")
    while acres_to_sell > acres:
        print "O great Hammurabi, we have but", acres, "acres of land!"
        acres_to_sell = input("How many acres will you sell? ")
    return acres_to_sell

def ask_to_feed(bushels):
    '''Ask user how many bushels they want to use for feeding. '''
    bushels_to_feed = input("How many bushels of grain do you wish to feed your people? ")
    while bushels_to_feed > bushels:
        print "O great Hammurabi, we have but", bushels, "bushels of grain!"
        bushels_to_feed = input("How many bushels of grain do you wish to feed your people? ")
    return bushels_to_feed

def ask_to_cultivate(acres, population, bushels):
    '''Ask user how much land they want to plant seed in '''
    acres_to_plant = input("How many acres do you wish to plant with seed? ")
    while acres_to_plant > acres or acres_to_plant > population * 10 or acres_to_plant > bushels:
        if acres_to_plant > acres:
            print "O great Hammurabi, we have but", acres, "acres of land!"
        elif acres_to_plant > population * 10:
            print "O great Hammurabi, our population is only", population, "!"
        else:
            print "O great Hammurabi, we have but", bushels, "bushels of grain!"
        acres_to_plant = input("How many acres do you wish to plant with seed? ")
    return acres_to_plant

def isPlague():
    '''Determine if there is palge'''
    randomNumber = random.randint(0, 100)
    if randomNumber > 15:
        return False
    else:
        print
        print "A horrible plague struck! Half the population died."
        return True

def numStarving(population, bushels):
    '''Calculate the number of people that starve'''
    if population * 20 > bushels:
        peopleStarved = population - bushels / 20
        if peopleStarved > population * 0.45:
            print
            print "You've been kicked out of office! "
            print "You starved", peopleStarved, "people in one year! "
            return -1
        return peopleStarved
    else:
        return 0

def numImmigrants(land, grainInStorage, population, numStarving):
    '''Calculate the number of people that immigrate to the city'''
    if numStarving > 0:
        return 0
    else:
        peopleImmigrating = (20 * land + grainInStorage) / (100 * population + 1)
        return peopleImmigrating

def getHarvest():
    '''Calculate the bushels of grain that harvest per acre'''
    return random.randint(1, 8)

def effectOfRats():
    '''Calculate the effect of Rats'''
    return random.randint(10, 30) / 100.0

def priceOfLand():
    '''Calculate the price of land'''
    return random.randint(16, 22)

def Hammurabi():
    '''main function'''
    print_intro()

    starved = 0
    immigrants = 5
    population = 100
    harvest = 3000    # total bushels harvested
    bushels_per_acre = 3 # amount harvested for each acre planted
    rats_ate = 200
    rat_probability = 0
    bushels_in_storage = 2800
    acres_owned = 1000
    cost_per_acre = 19    # each acre costs this many bushels
    plague_deaths = 0
    
    peopleTotalStarved = 0

    for i in range(1,11):
        print
        print "O great Hammurabi!"
        print "You are in year", i, "of your ten year rule."
        print "In the previous year", starved, "people starved to death."
        print "In the previous year", immigrants, "people entered the kingdom."
        print "The population is now", population, "."
        print "We harvested", harvest, "bushels at", bushels_per_acre, "bushels per acre."
        print "Rats destroyed", rats_ate, "bushels, leaving", bushels_in_storage, "bushels in storage." 
        print "The city owns", acres_owned, "acres of land." 
        print "Land is currently worth", cost_per_acre, "bushels per acre." 
        print "There were",plague_deaths, "deaths from the plague."
        print
        
        # Ask user how many bushels to spend buying land.
        acres_to_buy = ask_to_buy_land(bushels_in_storage, cost_per_acre)
        acres_owned = acres_owned + acres_to_buy
        bushels_in_storage = bushels_in_storage - acres_to_buy * cost_per_acre

        # Ask user how much land they want to sell.
        if acres_to_buy == 0:
            acres_to_sell = ask_to_sell_land(acres_owned)
            acres_owned = acres_owned - acres_to_sell
            bushels_in_storage = bushels_in_storage + acres_to_sell * cost_per_acre

        # Ask user how many bushels they want to use for feeding. 
        bushels_to_feed = ask_to_feed(bushels_in_storage)
        bushels_in_storage = bushels_in_storage - bushels_to_feed

        # Ask user how much land they want to plant seed in
        acres_to_plant = ask_to_cultivate(acres_owned, population, bushels_in_storage)
        bushels_in_storage = bushels_in_storage - acres_to_plant
        
        # Determine if there is palge
        if isPlague():
            plague_deaths = population / 2
            population = population - plague_deaths
        else:
            plague_deaths = 0

        # Calculate the number of people that starve
        starved = numStarving(population, bushels_to_feed)
        if starved == -1:
            break
        else:
            peopleTotalStarved = peopleTotalStarved + starved
            population = population - starved

        # Calculate the number of people that immigrate to the city
        immigrants = numImmigrants(acres_owned, bushels_in_storage, population, starved)
        population = population + immigrants

        # Calculate the bushels of grain that harvest per acre
        bushels_per_acre = getHarvest()
        harvest = acres_to_plant * bushels_per_acre

        # Calculate the effect of Rats
        rat_probability = random.randint(0, 100) / 100.0

        if rat_probability > 0.4:
            rats_ate = 0
        else:
            rats_ate = effectOfRats() * harvest
            rats_ate = int(rats_ate)
        bushels_in_storage = bushels_in_storage + harvest - rats_ate

        # Calculate the price of land
        cost_per_acre = priceOfLand()
        
    if starved != -1:
        print
        print "You've completed your 10 year term!!!"
        print
        print "In your 10-year term of office, a total of", peopleTotalStarved, "people died!!!"
        print "You started with 1000 acres of land and ended with", acres_owned, "acres."
        print
        print "****************************************************************"
        print
        print "Evaluation:"
        if peopleTotalStarved > 0:
            if peopleTotalStarved > 10:
                print "Due to this extreme mismanagement, you have not only been impeached and thrown out of office but you have also been declared 'National Fink'!!! "
            else:
                print "Your heavy-handed performance smacks of Nero and Ivan IV. The people find you an unpleasant ruler, and, frankly, hate your guts!"
        else:
            if acres_owned < 800:
                print "Your heavy-handed performance smacks of Nero and Ivan IV. The people find you an unpleasant ruler, and, frankly, hate your guts!"
            elif acres_owned < 1000:
                print "Your performance could have been somewhat better, but really wasn't too bad at all. "
            else:
                print "A fantastic performance!!! Charlemagne, Disraeli and Jefferson combined could not have done better!"
    else:
        print
        print "****************************************************************"
        print
        print "Evaluation:"
        print "Due to this extreme mismanagement, you have not only been impeached and thrown out of office but you have also been declared 'National Fink'!!! "


Hammurabi()
