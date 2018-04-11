def readFile():
    '''read the text file 'resume.txt' into list'''
    my_lines = []
    f = open('resume.txt','rU')
    line = f.readline()
    while line:
        my_lines.append(line)
        line = f.readline()
    f.close()
    return my_lines

def detectName(my_lines):
    '''extract the name from file lines'''
    name = [] 
    if my_lines[0].lstrip().rstrip() != '':   
        char = my_lines[0].lstrip().rstrip()[0]
        if char not in 'ABCDEFGHIJKLMNOPQRSTUVWXYZ':
            raise ValueError, "first line should be name with proper capitalization"
        name.append(my_lines[0])
    else :
        raise ValueError, "first line should be name with proper capitalization"
    return name

def detectEmail(my_lines):
    '''extract the email from file lines'''
    email = []
    for line in my_lines:
        if '@' in line:
            email_line = line.lstrip().rstrip() + '\n'
            if email_line[-5:-1] == '.com' or email_line[-5:-1] == '.edu':
                index = email_line.find('@')
                index += 1
                if email_line[index] in 'abcdefghijklmnopqrstuvwxyz':
                    email.append(email_line)
            break
    return email

def detectCourses(my_lines):
    '''extract courses from file lines'''
    courses = []
    for line in my_lines:
        if 'Courses' in line:
            courses_line = line.lstrip()[7:]
            index = 0
            while courses_line[index] not in 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz' and courses_line[index]!='\n':
                index += 1
            if courses_line[index]!='\n':
                courses.append(courses_line[index:])
            break
    return courses

def detectProjects(my_lines):
    '''extract projects from file lines'''
    projects = []
    for line in my_lines:
        if 'Projects' in line:
            index = my_lines.index(line)
            index += 1
            
            while my_lines[index].lstrip().rstrip()[0:10] != '----------':
                if my_lines[index].lstrip().rstrip() != '':
                    projects.append(my_lines[index])
                index += 1
            break
    return projects

def detectEducation(my_lines):
    '''extract education from file lines'''
    education = []
    for line in my_lines:
        if (('Master' in line) or ('Bachelor' in line) or ('Doctor' in line)) and (('University' in line) or ('university' in line)):
            education.append(line)
    return education

def modifyHTMl(file_name):
    '''modifies the HTML file'''
    f = open(file_name, 'r+')
    lines = f.readlines()
    f.seek(0)
    f.truncate()
    del lines[-1]
    del lines[-1]
    line = '<div id="page-wrap">\n'
    lines.append(line)
    f.writelines(lines)
    f.close()

def surround_block(tag, text):
    '''surrounds text with HTML tags'''
    lines = []
    line = '<' + tag + '>' + '\n'
    lines.append(line)
    lines.extend(text)
    line = '</' + tag + '>' + '\n'
    lines.append(line)
    return lines

def write_intro_section(file_name, name, email):
    '''write intro section of the resume'''
    f = open(file_name, 'a')
    if name != []:
        text = surround_block('h1', name)
    if email != []:
        text.extend(surround_block('p', ['Email: ' + email[0]]))
    f.writelines(surround_block('div', text))
    f.close()

def write_education_section(file_name, education):
    '''write education section of the resume'''
    if education != []:
        f = open(file_name, 'a')
        text = surround_block('h2', ['Education\n'])
        edu = []
        for degree in education:
            edu.extend(surround_block('li', degree))
        text.extend(surround_block('ul', edu))    
        f.writelines(surround_block('div', text))
        f.close()

def write_project_section(file_name, projects):
    '''write projects section of the resume'''
    if projects != []:
        f = open(file_name, 'a')
        text = surround_block('h2', ['Projects\n'])
        pro = []
        for project in projects:
            pro.extend(surround_block('li', surround_block('p', project)))
        text.extend(surround_block('ul', pro))    
        f.writelines(surround_block('div', text))
        f.close()

def write_course_section(file_name, courses):
    '''write courses section of the resume'''
    if courses != []:
        f = open(file_name, 'a')
        text = surround_block('h3', ['Courses\n'])
        text.extend(surround_block('span', courses))    
        f.writelines(surround_block('div', text))
        f.close()

def write_end(file_name):
    '''write the end part'''
    f = open(file_name, 'a')
    lines = ['</div>\n', '</body>\n', '</html>\n'] 
    f.writelines(lines)
    f.close()

def main():
    "use this commands that are commented out for the recitation"
    my_lines = readFile()
    name = detectName(my_lines)
    email = detectEmail(my_lines)
    courses = detectCourses(my_lines)
    projects = detectProjects(my_lines)
    education = detectEducation(my_lines)

    file_name = 'resume.html'
    modifyHTMl(file_name)
    write_intro_section(file_name, name, email)
    write_education_section(file_name, education)
    write_project_section(file_name, projects)
    write_course_section(file_name, courses)
    write_end(file_name)



    
    
if __name__=='__main__':
    main()
