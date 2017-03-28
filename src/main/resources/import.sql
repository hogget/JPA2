insert into profile(name, surname, number_of_plays, life_Motto, level, about_me,modification_counter)values("anna","jopek",101,"timeIsfairforall", 3,"smilingAroundPeople", 1);
insert into user(email, modification_counter, profile_id) value ('abcd@abd.com','1', (select max(id) from profile));

insert into profile(name, surname, number_of_plays, life_Motto, level, about_me,modification_counter)values("aneta","adrionek",102,"timeIsfairforall", 2,"hardworking", 1);
insert into user(email, modification_counter, profile_id) value ('aneta@abd.com','1',  (select max(id) from profile));

insert into profile(name, surname, number_of_plays, life_Motto, level, about_me,modification_counter)values("bartek","maciuszke",103,"neverendinglove", 4,"curiousOfTheWorld", 1);
insert into user(email, modification_counter, profile_id) value ('bartek@abd.com','1', (select max(id) from profile));

insert into profile(name, surname, number_of_plays, life_Motto, level, about_me,modification_counter)values("majka","konstanty",104,"honestyAndmodesty", 3,"trustyourself", 1);
insert into user(email, modification_counter, profile_id) value ('majka@abd.com','1', (select max(id) from profile));

insert into profile(name, surname, number_of_plays, life_Motto, level, about_me,modification_counter)values("basia","francuz",135,"liveyourlife", 4,"createAmazingThings", 1);
insert into user(email, modification_counter, profile_id) value ('basia@abd.com','1', (select max(id) from profile));

insert into profile(name, surname, number_of_plays, life_Motto, level, about_me,modification_counter)values("marek","jopekkkk",108,"timeIsfairforall", 6,"smilingAroundPeople", 1);
insert into user(email, modification_counter, profile_id) value ('abcd123@abd.com','1', (select max(id) from profile));

insert into profile(name, surname, number_of_plays, life_Motto, level, about_me,modification_counter)values("kinga","adrion",112,"timeIsfairforall", 8,"hardworking", 1);
insert into user(email, modification_counter, profile_id) value ('aneta123@abd.com','1',  (select max(id) from profile));

insert into profile(name, surname, number_of_plays, life_Motto, level, about_me,modification_counter)values("King","macius",80,"neverendinglove", 10,"curiousOfTheWorld", 1);
insert into user(email, modification_counter, profile_id) value ('bartek123@abd.com','1', (select max(id) from profile));

insert into profile(name, surname, number_of_plays, life_Motto, level, about_me,modification_counter)values("ula","wer",104,"honestyAndmodesty", 7,"trustyourself", 1);
insert into user(email, modification_counter, profile_id) value ('majka123@abd.com','1', (select max(id) from profile));

insert into profile(name, surname, number_of_plays, life_Motto, level, about_me,modification_counter)values("ola","good",10,"liveyourlife", 4,"createAmazingThings", 1);
insert into user(email, modification_counter, profile_id) value ('basia123@abd.com','1', (select max(id) from profile));

insert into challange(state, modification_counter,user_from_id,user_to_id)values("WAITING",1,1,2); 
insert into challange(state, modification_counter,user_from_id,user_to_id)values("ACCEPTED",1,1,3);
insert into challange(state, modification_counter,user_from_id,user_to_id)values("CANCELLED",1,1,4);
insert into challange(state, modification_counter,user_from_id,user_to_id)values("WAITING",1,1,5);
insert into challange(state, modification_counter,user_from_id,user_to_id)values("ACCEPTED",1,6,7);
insert into challange(state, modification_counter,user_from_id,user_to_id)values("CANCELLED",1,6,8);
insert into challange(state, modification_counter,user_from_id,user_to_id)values("WAITING",1,6,9);
insert into challange(state, modification_counter,user_from_id,user_to_id)values("ACCEPTED",1,6,10);
insert into challange(state, modification_counter,user_from_id,user_to_id)values("CANCELLED",1,3,2);
insert into challange(state, modification_counter,user_from_id,user_to_id)values("WAITING",1,3,2);
insert into challange(state, modification_counter,user_from_id,user_to_id)values("ACCEPTED",1,3,2);
insert into challange(state, modification_counter,user_from_id,user_to_id)values("CANCELLED",1,3,2);