package me.ksiazka.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import me.ksiazka.model.*;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {
        @JsonIgnore
        private Long id;
        private String name;
        private String surname;
        private String email;
        @JsonIgnore
        private String password;
        private String username;
        private List<Book> booksWant = new ArrayList<Book>(0);
        private List<UserBook> booksHave = new ArrayList<UserBook>(0);
        private List<Address> addressList = new ArrayList<Address>(0);
        private List<UserRole> roles = new ArrayList<UserRole>(0);
        private List<Message> messages = new ArrayList<Message>(0);
        private List<OfferRelation> offerList = new ArrayList<OfferRelation>(0);

        public  UserDTO() {

        }

        public UserDTO(String name, String username, String password, String email, String surname) {
            this.name = name;
            this.username = username;
            this.password = password;
            this.email = email;
            this.surname = surname;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        @JsonIgnore
        public List<Address> getAddressList() {
            return addressList;
        }

        public void setAddressList(List<Address> addressList) {
            this.addressList = addressList;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @JsonIgnore
        public List<Book> getBooksWant() {
            return booksWant;
        }

        private void setBooksWant(List<Book> booksWant) {
            this.booksWant = booksWant;
        }

        @JsonIgnore
        public List<UserBook> getBooksHave() {
            return booksHave;
        }

        private void setBooksHave(List<UserBook> booksHave) {
            this.booksHave = booksHave;
        }

        @JsonIgnore
        public List<UserRole> getRoles() {
            return roles;
        }

        public void setRoles(List<UserRole> roles) {
            this.roles = roles;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @JsonIgnore
        public List<Message> getMessages() {
            return messages;
        }

        public void setMessages(List<Message> messages) {
            this.messages = messages;
        }

        @JsonIgnore
        public List<OfferRelation> getOfferList() {
            return offerList;
        }

        public void setOfferList(List<OfferRelation> offerList) {
            this.offerList = offerList;
        }

        public int getSizeOfBooksWant() {
            return this.booksWant.size();
        }

        public Book getBookFromBooksWant(int index) {

            return this.booksWant.get(index);
        }

        public void removeFromWantList(Book book) {

            this.booksWant.remove(book);
        }

        public void removeFromWantList(int index) {

            this.booksWant.remove(index);
        }

        public int getSizeOfBooksHave() {

            return this.booksHave.size();
        }

        public UserBook getBookFromBooksHave(int index) {

            return this.booksHave.get(index);
        }

        public void removeFromHaveList(int index) {

            this.booksHave.remove(index);
        }

        public void removeFromHaveList(UserBook userBook) {

            this.booksHave.remove(userBook);
        }

}
