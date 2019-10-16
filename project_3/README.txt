project folder:
fuhui0310-project04/


Brief description of submitted files:

src/queues/

Jukebox.java
    -The class Jukebox to manage three objects of type Queue<String>.
    -The constructor reads the file with user's request for what song to add to which playlist.

Mytunes.java
    -Creates an object of type MyTunes which simulates a playlist queue.
    -Enqueues and dequeues SongEntry objects from each playlist.
    -Simulates playing each song and finally checks the state of each playlist.
    
Queue.java
    -The class Queue which implements Iterable. Objects of type Queue manage items in a singly linked list
    -where we can enqueue() from the front and dequeue() items from the end of the queue.

resources/

tunes.txt
    - input for showing non-empty queues.

tunes_truncated.txt
    - input for calling dequeue() method of class Queue when some queues are empty.
    
tunes_additional.txt
    - input for removing items from an empty queue.
    
music_genre_subset.json
    - a song list
    
RUN.txt
    - console output of DemoGit.java

README.txt
    - description of submitted files
    
docs/
