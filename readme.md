# 📁 Struktur Folder dan Panduan Penggunaan Package

**ToDoList App – Terminal Version**

Dokumen ini menjelaskan isi dan tanggung jawab masing-masing package dalam project **ToDoList berbasis terminal**, agar struktur kode tetap **rapi**, **modular**, dan **mudah dipelihara (maintainable)**.

---

## 📦 `entity/` – Entitas / Model

### 🎯 Tujuan:

Mewakili struktur data utama dari aplikasi (biasanya satu class per entitas).

### ✅ Isi yang diperbolehkan:

- Class POJO seperti `ToDo`, `User`, dll.
- Method: constructor, getter/setter, `toString()`, dan method utilitas kecil.
- Tidak mengandung logika selain pengelolaan data.

### ❌ Tidak boleh ada:

- Koneksi ke database.
- Logika bisnis.
- Input/output (I/O).

---

## 📦 `repository/` – Penyimpanan / Persistence Layer

### 🎯 Tujuan:

Mengelola interaksi dengan penyimpanan data permanen (file, database, dll).

### ✅ Isi yang diperbolehkan:

- Interface seperti `ToDoRepository`, `UserRepository` (sebagai kontrak).
- Implementasi penyimpanan seperti `FileBasedToDoRepository`, `InMemoryToDoRepository`.
- Koneksi ke database/file (dengan bantuan package `util` jika perlu).
- SQL Query dan pemetaan hasil query ke objek `entity`.

### ❌ Tidak boleh ada:

- Logika bisnis aplikasi.
- Interaksi dengan pengguna.

---

## 📦 `service/` – Lapisan Logika Bisnis (Business Logic)

### 🎯 Tujuan:

Mengatur aturan utama aplikasi dan pengolahan data sebelum ditampilkan atau disimpan.

### ✅ Isi yang diperbolehkan:

- Class seperti `ToDoService`, `UserService`.
- Proses validasi, filter, pengurutan (sorting), pengaturan status entitas, dll.
- Menghubungkan antara `repository` dan `view`.

### ❌ Tidak boleh ada:

- Koneksi langsung ke database.
- Interaksi langsung dengan terminal.

---

## 📦 `views/` – Terminal UI / Antarmuka Pengguna

### 🎯 Tujuan:

Berinteraksi langsung dengan pengguna lewat terminal.

### ✅ Isi yang diperbolehkan:

- Tampilan menu dan sub-menu.
- Input pengguna (menggunakan `Scanner`).
- Output ke terminal (`System.out.println`).
- Pemanggilan ke `service` berdasarkan perintah user.

### ❌ Tidak boleh ada:

- Koneksi database.
- Logika bisnis kompleks.
- Implementasi penyimpanan data.

---

## 📁 Struktur Folder Rekomendasi

```

src/
├── entity/
├── repository/
├── service/
├── views/
├── util/
└── Main.java

```

---

## ✅ Tips Tambahan

- **File konfigurasi** seperti `config.properties` sebaiknya disimpan di:

```

src/main/resources/config.properties

```

dan diakses menggunakan mekanisme `ClassLoader`.

- Gunakan package `util/` untuk class helper seperti:
- Koneksi database.
- Baca/tulis file.
- Generator UUID.
- Validasi sederhana.

- Gunakan pendekatan **dependency injection manual** untuk menyambungkan antar layer. Misalnya:
- `View` memanggil `Service`.
- `Service` menggunakan `Repository`.

- Untuk keperluan testing, bisa dibuat versi repository in-memory yang menyimpan data di dalam List, tanpa menyentuh file atau database.

---
