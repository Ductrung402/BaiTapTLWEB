<%@ include file="/includes/header.html" %>
<p><b>User Email:</b> ${cookie.userEmail.value}</p>

    <img src="images/Logo.jpg" alt="Murach Logo" width="100">

    <h1>Survey</h1>
    <p>If you have a moment, we'd appreciate it if you would fill out this survey.</p>

    <form action="survey" method="post">
        <input type="hidden" name="action" value="add">

        <h2>Your information:</h2>
        <div class="form-group">
            <label>First Name</label>
            <input type="text" name="firstName" required>
        </div>
        <div class="form-group">
            <label>Last Name</label>
            <input type="text" name="lastName" required>
        </div>
        <div class="form-group">
            <label>Email</label>
            <input type="email" name="email" required>
        </div>
        <div class="form-group">
            <label>Date of Birth</label>
            <input type="text" name="dob">
        </div>

        <h2>How did you hear about us?</h2>
        <div class="radio-group">
            <input type="radio" id="search" name="heardFrom" value="Search engine" checked>
            <label for="search">Search engine</label>

            <input type="radio" id="word" name="heardFrom" value="Word of mouth">
            <label for="word">Word of mouth</label>

            <input type="radio" id="social" name="heardFrom" value="Social Media">
            <label for="social">Social Media</label>

            <input type="radio" id="other" name="heardFrom" value="Other">
            <label for="other">Other</label>
        </div>

        <h2>Would you like to receive announcements about new CDs and special offers?</h2>
        <div class="checkbox-group">
            <input type="checkbox" id="likeThat" name="wantsUpdates" value="Yes">
            <label for="likeThat">YES, I'd like that.</label>
        </div>
        <div class="checkbox-group">
            <input type="checkbox" id="emailAnnounce" name="emailAnnouncements" value="Yes">
            <label for="emailAnnounce">YES, please send me email announcements.</label>
        </div>

        <div class="contact-group">
            <label for="contactMethod">Please contact me by:</label>
            <select name="contactVia" id="contactMethod">
                <option value="Email or postal mail">Email or postal mail</option>
                <option value="Email only">Email only</option>
                <option value="Postal mail only">Postal mail only</option>
            </select>
        </div>

        <input type="submit" value="Submit" id="submit">
    </form>

<%@ include file="/includes/footer.jsp" %>