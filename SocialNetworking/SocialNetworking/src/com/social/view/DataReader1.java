package com.social.view;


import java.util.ArrayList;
import java.util.List;

public class DataReader1 extends DataManager{
  
	@Override
    public List<User> getData() {

        List<User> userList = new ArrayList<>();
        User alice = new User();
        User bob = new User();
        User cathy = new User();
        User don = new User();


        //Data for alice

        List<Friend> friends = new ArrayList<>();
        Friend friend = new AdultFriend();
        /*friend.mUser = cathy;*/
        Friend friend1 = new AdultFriend();
        friend1.mUser = bob;
       // friends.add(friend);
        friends.add(friend1);


        Profile aliceProfile = new Profile();
        aliceProfile.setmFriendList(friends);
        aliceProfile.setName("Alice");
        aliceProfile.setStatus("Student");
        alice.setmProfile(aliceProfile);
        alice.setmAge(22);



        //data for bob
        friends = new ArrayList<>();
        friend = new AdultFriend();
        friend.mUser = alice;
        friend1 = new AdultFriend();
        //friend1.mUser = cathy;
        friends.add(friend);
        //friends.add(friend1);


        Profile bobProfile = new Profile();
        bobProfile.setmFriendList(friends);
        bob.setmAge(22);
        bobProfile.setName("Bob");
        bobProfile.setStatus("Student");
        bob.setmProfile(bobProfile);
        bob.setmAge(22);

        //Data for cathy

        friends = new ArrayList<>();
        friend = new AdultFriend();
        friend.mUser = bob;
        //friends.add(friend);


        Profile cathyProfile = new Profile();
        cathyProfile.setmFriendList(friends);
        //cathy.setmAge(22);
        cathyProfile.setName("Cathy");
        cathyProfile.setStatus("Student");
        cathy.setmProfile(cathyProfile);
        cathy.setmAge(15);
        List<User> parents = new ArrayList<>();
        parents.add(alice);
        parents.add(bob);
        cathy.setParents(parents);
        List<User> childrenList = new ArrayList<>();
        childrenList.add(cathy);
        alice.setChildren(childrenList);
        bob.setChildren(childrenList);


        userList.add(alice);
        userList.add(bob);
        userList.add(cathy);





        //Data for cathy




        //Data for don

        return userList;
    }

}
