package src.views;

import java.util.Scanner;

import com.zaxxer.hikari.HikariDataSource;

import src.entity.Person;
import src.repository.RepoPersonImpl;
import src.service.PersonServiceImpl;
import src.util.DatabaseUtil;

public class ViewsImpl implements Views {
    private Scanner userInput = new Scanner(System.in);
    private HikariDataSource dataSource = DatabaseUtil.getDataSource();
    private RepoPersonImpl repo = new RepoPersonImpl(dataSource);
    private PersonServiceImpl service = new PersonServiceImpl(repo);

    @Override
    public void mainMenu() {
        while (true) {
            this.clearScreen();
            System.out.println("""
                    ==== Aplikasi database CRUD person!===
                    1. Print Semua data
                    2. Tambah data
                    3. Edit data
                    4. hapus data
                    5. cari data
                    6. keluar
                    """);

            System.out.print("Masukkan pilihan :\n>");
            var userChoice = this.userInput.nextInt();
            userInput.nextLine();

            if (userChoice == 1) {
                this.getAllDataMenu();
            } else if (userChoice == 2) {
                this.addPersonMenu();

            } else if (userChoice == 3) {
                this.editPersonMenu();

            } else if (userChoice == 4) {
                this.removePersonMenu();

            } else if (userChoice == 5) {
                this.findPersonMenu();

            } else if (userChoice == 6) {
                break;
            }

            else {

            }

        }

    }

    @Override
    public void addPersonMenu() {
        this.clearScreen();
        System.out.println("=== MENU TAMBAH DATA ===");
        System.out.print("Masukan nama baru : ");
        String newName = userInput.nextLine();

        System.out.print("Masukan umur baru : ");
        int newAge = userInput.nextInt();
        userInput.nextLine();

        System.out.print("Masukan gender baru : ");
        String newGender = userInput.nextLine();

        this.service.addData(newName, newAge, newGender);

    }

    @Override
    public void editPersonMenu() {
        this.service.printAllData();
        System.out.print("Masukan (nama/id) dari data yg ingin diedit :\n>");
        String queryData = userInput.nextLine();
        Person oldPerson = this.service.findData(queryData);

        System.out.println(oldPerson);

        System.out.print("Masukan nama baru : ");
        String newName = userInput.nextLine();

        System.out.print("Masukan umur baru : ");
        int newAge = userInput.nextInt();
        userInput.nextLine();

        System.out.print("Masukan gender baru : ");
        String newGender = userInput.nextLine();

        this.service.editData(oldPerson, newName, newAge, newGender);
        this.enterToBack();

    }

    @Override
    public void removePersonMenu() {
        this.clearScreen();
        System.out.println("=== HAPUS DATA ===");
        this.service.printAllData();
        System.out.print("Masukan (nama/id) dari data yang ingin dihapus :\n>");
        String deletedQuery = userInput.nextLine();
        this.service.deleteData(deletedQuery);
        this.enterToBack();
    }

    @Override
    public void getAllDataMenu() {
        System.out.println("\n\n\n\nSemua data yang ada!!");
        this.service.printAllData();
        this.enterToBack();
    }

    @Override
    public void findPersonMenu() {
        this.clearScreen();
        System.out.print("Masukan query nama perncarian :\n>");
        String query = userInput.nextLine();
        this.service.findAllData(query);
        this.enterToBack();
    }

    private void enterToBack() {
        System.out.print("Tekan [enter] untuk kembali");
        this.userInput.nextLine();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
    }

}
