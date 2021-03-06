# PRG381 Project for 2021

Welcome to our Delicious-Catering console program! 

## Structure

We followed the buiness layers approach and indicated it with the directory structure.
- `dataaccess\layer` contains our data saving and loading logic.
- `bussinesslogic\layer` contains most of our object handling and tranformation logic, it also contains some presentation code.
- `presentation\layer` contatins all of the objects and methods to enable user interaction with the data objects.

Some smaller folder exist aswell:

- `controls` was used to orginize the difrent controls available by the clients and the admin.
- `menu` is used to hold the ConsoleMenu class that we used extensably in simplifing the logic of the creation of console menus.

## Data

We decided to go with serialization as our saving and loading method. The Files in the `Data` are where we store the persistant data.

# Sample

We included some sample data in the program.

## Admin Acount

Sign in with:
>Username: Raheal  
>Password: Admin

## Sample Clients

| First Name | Last Name | Number     |
| ---------- |  -------- | ---------- |
| John       | Smith     | 0123456789 |
| Megan      | Ross      | 0237896541 |
| Dian       | Brith     | 0124568869 |
| Ellie      | Prince    | 0789635460 |
| Theo       | Joost     | 0615239654 |
| Ruben      | Van Wyk   | 0629548745 |
| Thabo      | Madiba    | 0785649632 |
| Maggy      | Moabelo   | 0725603215 |

# The Team

 ### Wessel Scholtz
 Built the data structure from business layer.

 ### Marco Venter
 Coded the FileHandler class and added sample data.

 ### Ignatio Ferreira
 Coded and tested the presentation text.

 ### Charles Payne
 Coded menu logic and added sample data.
