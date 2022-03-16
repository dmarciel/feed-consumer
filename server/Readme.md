# Feed Consumer

This is a implementation of a feed consumer with two endpoints.
1. Load endpoint, it allows providers to load content data to the system.
2. Total provider endpoint, it returns the total amount of messages the provider has loaded. Considering message as a call to the first endpoint.

## Disclaimer

Since the documentation was very open, 
and many points weren´t settled,
I have taken the following considerations:

1. The second endpoint returns the "total" amount of messages a provider has loaded.
   Since it is not established what "total messages" means,
   it will return the total amount calls done by a single provider to the load endpoint.

2. There was no specific information about the input data I had to do reverse engineering.
   It took me the longest.
   I found a "glitch" in the documentation. 
   According to the documentation, recordIndex should be a int32 (int),
   I found out it was actually a int64 (long)

3. Since the business logic doesn´t need to have different behaviour than the incoming dto 
   I didn´t map the entities in new objects, if it is required please let me know.
   In this case I will add it to the development.
   
4. Due to the lack of test cases, and the reverse engineering of the development,
   I couldn´t use TDD properly.
   