# SQL-table-data-simulator

This project was a small foray into generating dummy data for a SQL database that came about while trying to populate a relational DB with data quickly to run some tests on. 

User enters the necessary table, column, and data ranges into the 4 string query template in the public static void in "table_filler.java". User must also specify number of rows.

The query template (and integer for number of rows) gets fed into the static generateQuery function from the "queryGenerator" class, which writes queries to a .sql file.

If database contains foreign keys fill tables in order of dependence. Foreign key number ranges for dependent tables should match number of entries in the table it depends on.
