def readFile(file_name)
    #read the text file 'resume.txt' into list
    my_lines = []
    f = File.open(file_name)
    f.each_line do |line|
	   my_lines << line
	end
	f.close	
    return my_lines
end

def detectName(my_lines)
    #extract the name from file lines
    name = []
    name = my_lines[0]
    if !name.empty?        
        if !name.match(/^[A-Z].*/)
            raise "First line should be name with proper capitalization"
       	end
    else
        raise "First line should be name with proper capitalization"
    end

    return [name]
end

def detectEmail(my_lines)
    #extract the email from file lines
    email = []
    my_lines.each do |line|
    	if line.include?('@')
            email_line = line
            if email_line.match(/^[a-zA-Z0-9_.]+@\w+[a-zA-Z0-9_.]+((\.com)|(\.edu))$/)                
                email = email_line
            end
            break
        end

    end
    return [email]
end

def detectCourses(my_lines)
    #extract courses from file lines
    courses = []
    courses_line = ""
    my_lines.each do |line|
    	if line.include?('Courses')
    		courses_line = line.strip[7..-1].match(/\w.*/)    		
    		break
    	end
    end
    
   	courses << courses_line.to_s + "\n"

    return courses
end

def detectProjects(my_lines)
    #extract projects from file lines
    projects = []
    my_lines.each do |line|
    	if line.include?('Projects')
    		index = my_lines.index(line)
            index += 1
            while (my_lines[index].strip[0..9] != '----------') && (index < my_lines.length-1)
            	if !my_lines[index].strip.empty?
            		projects << my_lines[index]
            	end
            	index += 1
            end    		    		
    		break
    	end
    end

    return projects
end
   

def detectEducation(my_lines)
    #extract education from file lines
    education = []
    my_lines.each do |line|
    	if line.include?('university') or line.include?('University')
    		if line.include?('Bachelor') or line.include?('Master') or line.include?('Doctor')
    			education << line
    		end
    	end
    end

    return education
end

def modifyHTMl(file_name)
    #modifies the HTML file
    my_lines = []
    f = File.open(file_name,"r+")
    f.each_line do |line|
	   my_lines << line
	end

	my_lines.delete_at(-1)
	my_lines.delete_at(-1)

	
	line = '<div id="page-wrap">'
	my_lines << line

	f.truncate(0)
	f.close
	
	f = File.open(file_name,"w")
	my_lines.each do |my_line|
		f.write(my_line)
	end

	f.close
    
end

def surround_block(tag, text)
    #surrounds text with HTML tags
    lines = []
    line = "<" + tag + ">" + "\n"
    lines << line
    # text.map! {|item| item += "\n"}
    lines += text

    line = "</" + tag + ">" + "\n"
    lines << line
    return lines
end

def write_intro_section(file_name, name, email)
    #write intro section of the resume
    text = []
   	
    if name != []
       	text += surround_block("h1", name)
    end
    if email != []
        text += surround_block("p", ["Email: "] + email)
    end

    f = File.open(file_name,"a")
	text.each do |my_line|
		f.write(my_line)
	end
	f.close
end

def write_education_section(file_name, education)
    #write education section of the resume
    text = []
    if education != []
        text += surround_block("h2", ["Education\n"])
        edu = []
        education.each do |degree|
            edu += surround_block("li", [degree])
        end
        text += surround_block("ul", edu)        
        text = surround_block("div", text)
    end

    f = File.open(file_name,"a")
	text.each do |my_line|
		f.write(my_line)
	end
	f.close        
end

def write_project_section(file_name, projects)
	text = []
    #write projects section of the resume
    if projects != []
        text += surround_block("h2", ["Projects\n"])
        pro = []
        projects.each do |project|
            pro += (surround_block("li", surround_block("p", [project])))
        end
        text += (surround_block("ul", pro))           
    end

    f = File.open(file_name,"a")
	text.each do |my_line|
		f.write(my_line)
	end
	f.close     
end

def write_course_section(file_name, courses)
    #write courses section of the resume
    text = []
    if courses != []
        text += surround_block("h3", ["Courses\n"])
        text += surround_block("span", courses)
    end 

    f = File.open(file_name,"a")
	text.each do |my_line|
		f.write(my_line)
	end
	f.close  
end

def write_end(file_name)
    #write the end part
    f = open(file_name, "a")
    text = ["</div>\n", "</body>\n", "</html>\n"] 
    text.each do |my_line|
		f.write(my_line)
	end
	f.close  
end

def main()
    
	my_lines = readFile("resume.txt")
    name = detectName(my_lines)
    email = detectEmail(my_lines)
    courses = detectCourses(my_lines)
    projects = detectProjects(my_lines)
    education = detectEducation(my_lines)

    file_name = "resume.html"
    modifyHTMl(file_name)
    write_intro_section(file_name, name, email)
    write_education_section(file_name, education)
    write_project_section(file_name, projects)
    write_course_section(file_name, courses)
    write_end(file_name)

end

# main()



