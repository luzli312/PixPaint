# 🎨PixPaint

## :notebook: Table of Contents

- [About the Project](#pushpin-about-the-project)
  * [Summary](#scroll-summary)
  * [Features](#star2-features)
  * [How to Use](#pencil-how-to-use)
  * [Authors](#stars-authors)
- [Installation](#hammer_and_wrench-installation)
- [License](#warning-license)
- [Feedback](#loudspeaker-feedback)
- [Contributing](#handshake-contributing)
  * [How to Contribute](#spiral_notepad-how-to-contribute)
  * [Guidelines for Good Merge Request](#ballot_box_with_check-guidelines-for-good-merge-request)
  * [Merge Review Protocol](#mag-merge-review-protocol)

## :pushpin: About the Project

### :scroll: Summary
**PixPaint** is a simple Java based pixel drawing app that lets anyone start creating pixel art within seconds on a 32x32 grid canvas. The app works on both Windows and MacOS.
- **Why we built it:** We wanted to make a lightweight drawing tool that is easy to understand and saves work online, so you can pick up where you left off on any device.
- **What problem it solves:** Many art tools are complicated and overwhelming. PixPaint is fast and beginner-friendly; it ensures your work is safely stored in the cloud and easy to retrieve.

### :star2: Features
- :paintbrush: **Brush Tool:** Draw pixel-by-pixel with your selected colour
- :sponge: **Eraser Tool:** Removes pixels instantly
- :cloud: **Save to MongoDB:** Store your work under your account and reload it anytime
- :file_folder: **Load from MongoDB:** Pick up from exactly where you left off
- :floppy_disk: **Export to PNG:** Save your finished artwork locally
- :bust_in_silhouette: **User Accounts:** Log in to your account to access your artworks
- :rocket: **Easy to Use:** Clean interface that can be used by both beginners and experts

### :pencil: How to Use
1. Log in/Sign up to your account. <br>
   <img width="323" height="178" alt="Screenshot 2025-08-14 at 12 11 25 PM" src="https://github.com/user-attachments/assets/db58afa8-c9e9-4ead-9cf6-681e6bb8dbf6" />
2. Draw your art piece, using the brush/eraser tool. Change the palette's color by rightclicking on the color tiles.
   <img width="965" height="724" alt="Screenshot 2025-08-14 at 12 20 19 PM" src="https://github.com/user-attachments/assets/c1a576ce-9789-402a-a684-f6aab9935f35" />
   <img width="960" height="720" alt="Screenshot 2025-08-14 at 12 12 59 PM" src="https://github.com/user-attachments/assets/e87efb66-8aa7-4793-9508-d58a1aabab9d" />
3. Export your finished art piece as a PNG! <br>
   <img width="640" height="640" alt="bunny" src="https://github.com/user-attachments/assets/1e96978f-e5c8-4399-a476-07e3ecaa14c6" />

### :stars: Authors
- [Elizabeth Li](https://github.com/luzli312)
- [Emily Escalante](https://github.com/EEsc05)
- [Katie Lee](https://github.com/katfishy)

## :hammer_and_wrench: Installation
1. Create a MongoDB Account. Get your [MongoDB connection string](https://www.mongodb.com/docs/drivers/java/sync/current/get-started/#create-a-connection-string).
2. Clone the repository.
   ```
   git clone https://github.com/luzli312/PixPaint.git
   cd PixPaint
   ```
3. Set up MongoDB token.
   - In the project folder, create a file named: `token.txt`
   - Paste your account's **MongoDB connection string** inside it.
4. Open the project in IntelliJ. If prompted, set the Project SDK to Java 17 or higher.
5. Click the Run button after navigating to `src/main/java/app/Main.java`

**Common Issues & Fixes**
| Issue                             | Cause                                                              | Fix                                                                            |
| -------------- | ------------------------------------------------------------------ | ------------------------------------------------------------------------------ |
| **Package view does not exist** | IntelliJ did not set the `src/main/java` folder as the source root. | Right-click `src/main/java` → **Mark Directory as → Sources Root**.            |
| **Cannot connect to MongoDB**     | Wrong token or network issue.                               | Verify the contents of `token.txt` and network connection.              

## :warning: License
This project is licensed under the [MIT License](https://github.com/luzli312/PixPaint/blob/main/LICENSE).

## :loudspeaker: Feedback
Send feedback via our [Google Form](https://forms.gle/sfDrBu71eR9VWkoz6).

**Feedback Guidelines:**
- Be clear and constructive.
- Include steps to reproduce bugs that you witnessed.
- Avoid spam or offensive language please.

## :handshake: Contributing

### :spiral_notepad: How to Contribute
Contributions are always welcome! Follow the steps below.

**Forking the project:**
1. Go to [PixPaint repository](https://github.com/luzli312/PixPaint) on GitHub.
2. Click the **Fork** button on the top-right corner to create your own copy.
3. Clone your fork to your local machine:
   ```
   git clone https://github.com/your-username/PixPaint.git
   cd PixPaint
   ```

**Making your Changes**
1. Create a branch of your changes.
   ```
   git checkout -b feature/your-feature
   ```
2. Make your edits in the codebase.
3. Commit your changes with a clear message.
   ```
   git commit -m "Descriptive message."
   ```
4. Push your branch to your fork.
   ```
   git push origin feature/your-feature
   ```
5. Submit a merge request (pull request) with your changes.

### :ballot_box_with_check: Guidelines for Good Merge Request
- Keep requests focused on one feature or bug fix at a time.
- Follow the existing code style (naming conventions, comments, etc.)
- Test your changes to make sure it works
- If your request changes the UI, add screenshots in the PR description.

### :mag: Merge Review Protocol
- At least one author will review your PR once it is submitted.
- Feedback will be provided if any changes are needed.
- PRs that meet all guidelines and pass tests will be merged into the `main` branch.
