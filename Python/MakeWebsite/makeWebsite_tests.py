# authors -  Bo Liu and Zi Chen
from makeWebsite import *
import unittest

class TestMovies(unittest.TestCase):
    
    file_name = ''
    
    def setUp
        self.file_name = 'resume_test.html'

    def test_detectName
        name = detectName(['Zi Chen\n','\n'])
        assert_equal(['Zi Chen\n'],name)

        self.assertRaises(ValueError,detectName,['zi chen\n','\n'])
        
        self.assertRaises(ValueError,detectName,['\n','\n'])

    def test_detectEmail
        email = detectEmail(['zichen\n','zichen@seas.upenn.edu\n','\n'])
        assert_equal(['zichen@seas.upenn.edu\n'],email)

        email = detectEmail(['zichen\n','zichen@gmail.com\n','\n'])
        assert_equal(['zichen@gmail.com\n'],email)

        email = detectEmail(['\n','\n'])
        assert_equal([],email)

        email = detectEmail(['\n','zichen@seas.upenn.gov\n'])
        assert_equal([],email)

        email = detectEmail(['\n','zichen@Seas.upenn.edu\n'])
        assert_equal([],email)

        email = detectEmail(['\n','zichen@.edu\n'])
        assert_equal([],email)

    def test_detectCourses
        courses = detectCourses(['\n','Courses: CIT590, CIS505\n'])
        assert_equal(['CIT590, CIS505\n'],courses)

        courses = detectCourses(['\n','courses: CIT590, CIS505\n'])
        assert_equal([],courses)

        courses = detectCourses(['\n','Courses: -- -- CIT590, CIS505\n'])
        assert_equal(['CIT590, CIS505\n'],courses)

        courses = detectCourses(['\n','Courses: --  \n'])
        assert_equal([],courses)

    def test_detectProjects
        projects = detectProjects(['\n','Projects','built a network\n','\n','did an experiment\n','\n','----------\n'])
        assert_equal(['built a network\n','did an experiment\n'],projects)

        projects = detectProjects(['\n','projects','built a network\n','\n','did an experiment\n','\n','----------\n'])
        assert_equal([],projects)

        projects = detectProjects(['\n','Projects','built a network\n','\n','\n','----------\n','did an experiment\n'])
        assert_equal(['built a network\n'],projects)

    def test_detectEducation
        education = detectEducation(['Master of Science in CS - University of Pennsylvania\n','Bachelor of Science - Tsinghua University\n'])
        assert_equal(['Master of Science in CS - University of Pennsylvania\n','Bachelor of Science - Tsinghua University\n'],education)

        education = detectEducation(['Master of Science in CS - university of Pennsylvania\n','Bachelor of Science - Tsinghua university\n'])
        assert_equal(['Master of Science in CS - university of Pennsylvania\n','Bachelor of Science - Tsinghua university\n'],education)

        education = detectEducation(['Science in CS - University of Pennsylvania\n','Master of Science - MIT\n','projects in Stanford\n'])
        assert_equal([],education)

    def test_surround_block        
        lines = surround_block('p', ['Hello\n', 'World\n'])
        assert_equal(lines,['<p>\n', 'Hello\n', 'World\n', '</p>\n'])
        lines = surround_block('div', surround_block('p', ['Hello\n', 'World\n']))
        assert_equal(lines,['<div>\n', '<p>\n', 'Hello\n', 'World\n', '</p>\n', '</div>\n'])
    
    def test_modifyHTMl
        modifyHTMl(self.file_name)
        f = open(self.file_name, 'rU')
        lines = f.readlines()
        f.close()
        assert_equal(lines[-1:], ['<div id="page-wrap">\n'])
        self.assertFalse(set(lines) & set(['</body>\n']))
        self.assertFalse(set(lines) & set(['</html>\n']))
    
    def test_write_intro_section
        name = ['Arvind\n']
        email = ['bhusnur@seas.upenn.edu\n']

        write_intro_section(self.file_name, name, email)
        f = open(self.file_name, 'rU')
        lines = f.readlines()
        f.close()
        self.assertTrue(set(lines) & set(name))
        self.assertTrue(set(lines) & set(['Email: ' + email[0]]))

    def test_write_education_section
        write_education_section(self.file_name, [])
        f = open(self.file_name, 'rU')
        lines = f.readlines()
        f.close()
        self.assertFalse(set(lines) & set(['Education\n']))

        education = ['Bachelors in Python\n', 'Masters in Java\n']
        write_education_section(self.file_name, education)
        f = open(self.file_name, 'rU')
        lines = f.readlines()
        f.close()
        self.assertTrue(set(lines) & set(education))

    def test_write_project_section
        write_project_section(self.file_name, [])
        f = open(self.file_name, 'rU')
        lines = f.readlines()
        f.close()
        self.assertFalse(set(lines) & set(['Projects\n']))

        projects = ['Worked on the foreground background segmentation problem, which is a classic problem in computer vision.\n', 'Compiled data on grade inflation. Published several papers on this topic in the journal of ivy league humor.\n']
        write_project_section(self.file_name, projects)
        f = open(self.file_name, 'rU')
        lines = f.readlines()
        f.close()
        self.assertTrue(set(lines) & set(projects))

    def test_write_course_section
        write_course_section(self.file_name, [])
        f = open(self.file_name, 'rU')
        lines = f.readlines()
        f.close()
        self.assertFalse(set(lines) & set(['Courses\n']))
        
        courses = ['Algorithms, Forex training\n']
        write_course_section(self.file_name, courses)
        f = open(self.file_name, 'rU')
        lines = f.readlines()
        f.close()
        self.assertTrue(set(lines) & set(courses))

    def test_write_end
        write_end(self.file_name)
        f = open(self.file_name, 'rU')
        lines = f.readlines()
        f.close()
        assert_equal(lines[-3:], ['</div>\n', '</body>\n', '</html>\n'])

                
unittest.main()
