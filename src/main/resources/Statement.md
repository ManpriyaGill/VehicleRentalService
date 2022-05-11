# Vehicle Rental

### The Challenge
Rent different kinds of vehicles such as cars and bikes.


### Features:
1. Rental service has multiple branches throughout the city.
2. Each branch has a limited number of different kinds of vehicles.
3. Each vehicle can be booked with a predefined fixed price.
4. Each vehicle can be booked in multiples of 1-hour slots each. (For simplicity, assume slots of a single day)

### Requirements:
1. Onboard a new branch with available vehicles.
2. Onboard new vehicle(s) of an existing type to a particular branch.
3. Rent a vehicle for a time slot and a vehicle type(the lowest price as the default choice extendable to any other strategy).
4. Display available vehicles for a given branch sorted on price.
5. The vehicle will have to be dropped at the same branch where it was picked up.

**Bonus question:**
6. Dynamic pricing – demand vs supply. If 80% of cars in a particular branch are booked, increase the price by 10%.

### Input and Output scenarios:

| **Command**      | **Output**                                 | **Description**                                                                                  |
|------------------|--------------------------------------------|--------------------------------------------------------------------------------------------------|
| ADD_BRANCH       | TRUE if the operation succeeds else FALSE  | onboard branch (Branch Name, Vehicle Type)                                                       |
| ADD_VEHICLE      |  TRUE if the operation succeeds else FALSE | onboard vehicle - (Branch Name, Vehicle Type, vehicle id, price)                                 |
| BOOK             | Booking Price, if booking succeeds else -1 | book a vehicle - (Branch Name, vehicle type, start time, end time)                               |
| DISPLAY_VEHICLES | Vehicle Ids, comma-separated               | display all available vehicles for a branch, sorted on price ( Branch id, start time, end time ) |

**Check [this](inputs.txt) for sample input** 


### Other Details:
1. Use the in-memory store.
2. Do not create any UI for the application.
3. Write a driver class for demo purposes. Which will execute all the commands in one place in the code and test cases.
4. Prioritize code compilation, execution, and completion.
5. Work on the expected output first and then add good-to-have features of your own.

### Expectations:
1. Make sure that you can execute your code and show that it is working.
2. Make sure that the code is functionally correct.
3. Work on the expected output first and then add good-to-have features of your own.
4. Code should be modular and readable.
5. Separation of concern should be addressed.
6. Code should easily accommodate new requirements with minimal changes.
7. Code should be easily testable.
   
### Keep in mind:
1. Separation of concerns must be taken care.
2. Solution should be extensible.
3. Necessary design patterns must be implemented.
4. Naming variable conventions.
5. Input needs to be read from a text file, and output should be printed to console.