package com.fresco;

import java.util.*;

class Library {
    String bookName;
    String author;

    public Library() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return Objects.equals(bookName, library.bookName) && Objects.equals(author, library.author);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode((this.bookName));
        hash = 83 * hash + Objects.hashCode((this.author));
        return hash;
    }

    public Library(String bookName, String author) {
        this.bookName = bookName;
        this.author = author;
    }

    public HashMap<Integer, Library> createLibraryMap(String booksInLibrary) {
        String booksDetails[] = booksInLibrary.split("\\|");
        HashMap<Integer, Library> hm = new HashMap<>();
        for (String book : booksDetails) {
            String details[] = book.split("\\,");
            hm.put(Integer.valueOf(details[0]), new Library(details[1], details[2]));
        }
        return hm;
    }

    public HashMap<Integer, Integer> createUserMap(String borrowedUsers) {
        String userDetails[] = borrowedUsers.split("\\|");
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (String book : userDetails) {
            String details[] = book.split("\\,");
            hm.put(Integer.valueOf(details[0]), new Integer(details[1]));
        }
        return hm;
    }

    public String getQuery(String booksInLibrary, String borrowedUsers, String query) {
        HashMap<Integer, Library> library = createLibraryMap(booksInLibrary);
		  HashMap<Integer, Integer> barrow = createUserMap(borrowedUsers);
        StringBuilder sb = new StringBuilder();
        String[] queryDetails = query.split(",");
        switch (queryDetails[0]) {
            case "1": {
                int bookid = Integer.valueOf(queryDetails[1]);
                if (barrow.get(bookid) == null) {
                    sb.append("It is available\nAuthor is ");
                    sb.append(library.get(bookid).author);
                    sb.append("\n");
                } else {
                    sb.append("No Stock\nIt is owned by ");
                    sb.append(barrow.get(bookid));
                    sb.append("\n");
                }
                break;
            }
            case "2": {
                int userId = Integer.valueOf(queryDetails[1]);
                for (int key : barrow.keySet()) {
                    if (barrow.get(key) == userId) {
                        sb.append(key + " ");
                        sb.append(library.get(key).bookName);
                        sb.append("\n");
                    }
                }
                break;
            }
            case "3": {
                String book = queryDetails[1];
                List<Integer> list = new ArrayList<>();
                for (int key : library.keySet()) {
                    if (library.get(key).bookName.equals(book))
                    list.add(key);
                }
                int present = 0, borrowed = 0;
                for (int key : list) {
                    if (barrow.get(key) == null) 
                      present++;
                    else 
                      borrowed++;
                }
                sb.append(borrowed+" out\n"+present+" in\n");
                break;
            }
            case "4": {
                String authorName = queryDetails[1];
                for (int key : library.keySet()) {
                    if (library.get(key).author.equals(authorName))
                        sb.append(library.get(key).bookName + "\n");
                }
                break;
            }
            case "5": {
                String searchString = queryDetails[1].toLowerCase();
                for (int key : library.keySet()) {
                    if (library.get(key).bookName.toLowerCase().contains(searchString) || library.get(key).bookName.toLowerCase().contains(searchString))
                        sb.append(key + " " + library.get(key).bookName + "\n");
                }
                break;
            }
            default:
                break;

        }
        return sb.toString();
    }
}